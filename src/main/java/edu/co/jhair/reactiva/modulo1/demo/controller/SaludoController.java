package edu.co.jhair.reactiva.modulo1.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
public class SaludoController {

    @GetMapping("/user")
    public Mono<String> saludo(Mono<Principal> principalMono){
        return principalMono.map(Principal::getName)
                .map(name -> String.format("Hola %s", name));
    }

    @GetMapping("/admin")
    public Mono<String> saludoAdmin(Mono<Principal> principal) {
        return principal
                .map(Principal::getName)
                .map(name -> String.format("Acceso Administrador: %s", name));
    }
}