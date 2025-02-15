package org.esteban.userservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.esteban.userservice.entity.Person;
import org.esteban.userservice.models.ClientDTO;
import org.esteban.userservice.models.PersonDTO;
import org.esteban.userservice.service.ClientService;
import org.esteban.userservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("DMHouse")
@Tag(name = "User", description = "Manage user-related operations")
public class UserController {

    private final PersonService personService;
    private final ClientService clientService;

    public UserController(PersonService personService, ClientService clientService) {
        this.personService = personService;
        this.clientService = clientService;
    }


    @GetMapping("/person/{id}")
    @Operation(summary = "Get User by ID", description = "Retrieve user details based on the provided user ID")
    public ResponseEntity<Person> getUser(@PathVariable("id") Integer id) {
        Optional<Person> user = personService.getPersonById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/person")
    @Operation(summary = "Create User", description = "Create a new user")
    public ResponseEntity<String> createUser(@RequestBody ClientDTO clientDTO) {
        try {
            personService.createPerson(clientService.registerUser(clientDTO));
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user");
        }
    }
}
