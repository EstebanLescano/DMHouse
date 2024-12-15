package org.esteban.lescano.dmhouse.services;

import lombok.extern.slf4j.Slf4j;
import org.esteban.lescano.dmhouse.Exceptions.ClientAlreadyExistsException;
import org.esteban.lescano.dmhouse.entities.Client;
import org.esteban.lescano.dmhouse.entities.Person;
import org.esteban.lescano.dmhouse.repository.ClientRepository;
import org.esteban.lescano.dmhouse.repository.PersonRepository;
import org.springframework.stereotype.Service;

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
