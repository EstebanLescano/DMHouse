package org.esteban.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {
    @GetMapping("/ping")
    public String ping() {
        return "Gateway is working!";
    }
}
