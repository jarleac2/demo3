package edu.co.jhair.reactiva.modulo1.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
@RestController
public class UserController {

    @GetMapping("/yo")
    public String yo(Principal principal){
        return "Hola " + principal.getName();
    }
}
