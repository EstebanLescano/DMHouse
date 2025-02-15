package org.esteban.userservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.esteban.userservice.entity.Person;
import org.esteban.userservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("DMHouse")
@Tag(name = "User", description = "Manage user-related operations")
public class UserController {

    @Autowired
    private PersonService personService;

    public UserController() {
    }

    @GetMapping("/users/{id}")
    @Operation(summary = "Get User by ID", description = "Retrieve user details based on the provided user ID")
    public ResponseEntity<Person> getUser(@PathVariable("id") Integer id) {
        Optional<Person> user = personService.getPersonById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
