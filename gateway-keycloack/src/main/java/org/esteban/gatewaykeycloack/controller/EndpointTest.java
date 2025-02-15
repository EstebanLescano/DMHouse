package org.esteban.gatewaykeycloack.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class EndpointTest {
    @GetMapping("/anonymous")
    public String test() {
        return "Test endpoint";
    }
    @GetMapping("/admin")
    public String testAdmin() {
        return "Test endpoint admin";
    }
    @GetMapping("/user")
    public String testUser() {
        return "Test endpoint user";
    }
    @GetMapping("/all")
    public String testAll() {
        return "Test endpoint all";
    }
}
