package org.esteban.lescano.dmhouse.controller;

import org.esteban.lescano.dmhouse.Exceptions.ClientAlreadyExistsException;
import org.esteban.lescano.dmhouse.entities.Client;
import org.esteban.lescano.dmhouse.models.AuthRequest;
import org.esteban.lescano.dmhouse.models.AuthResponse;
import org.esteban.lescano.dmhouse.security.JwtTokenUtil;
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
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;
    private final ClientService clientService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserDetailsService userDetailsService, ClientService clientService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.clientService = clientService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            authRequest.getFullname(),
            authRequest.getPassword()
        ));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getFullname());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
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


