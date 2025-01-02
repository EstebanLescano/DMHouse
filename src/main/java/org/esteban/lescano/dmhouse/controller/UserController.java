package org.esteban.lescano.dmhouse.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "Manage user-related operations")
public class UserController {

    @GetMapping("/{id}")
    @Operation(summary = "Get User by ID", description = "Retrieve user details based on the provided user ID")
    public String getUser(@PathVariable("id") Long id) {
        return "User with ID: " + id;
    }
}
