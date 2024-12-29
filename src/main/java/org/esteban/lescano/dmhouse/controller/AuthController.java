package org.esteban.lescano.dmhouse.controller;

import org.esteban.lescano.dmhouse.Exceptions.ClientAlreadyExistsException;
import org.esteban.lescano.dmhouse.entities.Client;
import org.esteban.lescano.dmhouse.models.AuthRequest;
import org.esteban.lescano.dmhouse.models.AuthResponse;
import org.esteban.lescano.dmhouse.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController
 */
@RestController
@RequestMapping("/DMHouse")
public class AuthController {

    private final ClientService clientService;

    public AuthController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        try {
            // Lógica para interactuar con Keycloak y obtener el token
            String token = clientService.authenticateWithKeycloak(authRequest.getFullname(), authRequest.getPassword());

            return ResponseEntity.ok(new AuthResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/register")
     public ResponseEntity<String> register(@RequestBody Client client) {
        try {
            clientService.registerClient(client);
            return ResponseEntity.ok("Client registered successfully");
        } catch (ClientAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}


