package org.esteban.lescano.dmhouse.services;

import lombok.extern.slf4j.Slf4j;
import org.esteban.lescano.dmhouse.Exceptions.ClientAlreadyExistsException;
import org.esteban.lescano.dmhouse.entities.Client;
import org.esteban.lescano.dmhouse.entities.Person;
import org.esteban.lescano.dmhouse.repository.ClientRepository;
import org.esteban.lescano.dmhouse.repository.PersonRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final PersonRepository personRepository;

    public ClientService(ClientRepository clientRepository, PersonRepository personRepository) {
        this.clientRepository = clientRepository;
        this.personRepository = personRepository;
    }

    public String authenticateWithKeycloak(String username, String password) {
//         Configurar los detalles de la conexión con Keycloak
        String keycloakUrl = "https://your-keycloak-server/auth/realms/dmhouse/protocol/openid-connect/token";
        String clientId = "admin";
        String clientSecret = "admin";

        // Crear encabezados explícitamente
    HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    // Crear el cuerpo de la solicitud
    MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
    body.add("grant_type", "password");
    body.add("client_id", clientId);
    body.add("client_secret", clientSecret);
    body.add("username", username);
    body.add("password", password);

//     Crear el objeto HttpEntity
    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

//     Realizar la solicitud a Keycloak
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<Map> response = restTemplate.postForEntity(keycloakUrl, request, Map.class);

    if (response.getStatusCode() == HttpStatus.OK) {
        Map<String, Object> responseBody = response.getBody();
        if (responseBody != null && responseBody.containsKey("access_token")) {
            return responseBody.get("access_token").toString();
        }
    }

    throw new RuntimeException("Failed to authenticate with Keycloak");
}


    public void registerClient(Client client) {
        if (client == null || client.getEmail() == null || client.getPassword() == null) {
            throw new IllegalArgumentException("Client object, email, and password are required");
        }
        if (client.getPerson() == null || client.getPerson().getId() == null) {
            throw new IllegalArgumentException("Person must be provided and already exist");
        }
        Optional<Person> existingPerson = personRepository.findById(client.getPerson().getId());
        if (existingPerson.isEmpty()) {
            throw new IllegalArgumentException("Person with the given ID does not exist");
        }
        Optional<Client> existingClient = clientRepository.findByEmail(client.getEmail());
        if (existingClient.isPresent()) {
            throw new ClientAlreadyExistsException("Client with this email already exists");
        }
        client.setPerson(existingPerson.get());
        clientRepository.save(client);
    }
}
