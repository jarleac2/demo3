package edu.co.jhair.reactiva.modulo1.demo.controller.v2;

import edu.co.jhair.reactiva.modulo1.demo.model.Cliente;
import edu.co.jhair.reactiva.modulo1.demo.service.v2.ClienteService2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("v2/cliente")
public class ClienteController2 {

    ClienteService2 clienteService2;


    public ClienteController2(ClienteService2 clienteService2) {
        this.clienteService2 = clienteService2;
    }

    @GetMapping("/inactivos")
    public Flux<Cliente> getCreditosInactivos() {
        return clienteService2.findInactivos();
    }

}
