package edu.co.jhair.reactiva.modulo1.demo.service.v2;

import edu.co.jhair.reactiva.modulo1.demo.model.Cliente;
import edu.co.jhair.reactiva.modulo1.demo.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class ClienteService2 {

    private final Logger LOGGER = LoggerFactory.getLogger(ClienteService2.class);

    private final ClienteRepository clienteRepository;

    public ClienteService2(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Flux<Cliente> findInactivos() {
        return clienteRepository.findByActivo(Boolean.FALSE)
                .onErrorResume(throwable -> {
                    LOGGER.error("Error al buscar Clientes inactivos ", throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Clientes inactivos no encontrados").getMostSpecificCause()));
    }
}
