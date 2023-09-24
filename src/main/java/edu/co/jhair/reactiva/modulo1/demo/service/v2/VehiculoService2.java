package edu.co.jhair.reactiva.modulo1.demo.service.v2;

import edu.co.jhair.reactiva.modulo1.demo.model.Vehiculo;
import edu.co.jhair.reactiva.modulo1.demo.repository.VehiculoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VehiculoService2 {

    private final VehiculoRepository vehiculoRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(VehiculoService2.class);

    public VehiculoService2(VehiculoRepository vehiculoRepository){
        this.vehiculoRepository = vehiculoRepository;
    }

    public Flux<Vehiculo> findTodos() {
        return vehiculoRepository.findAll()
                .onErrorResume(throwable -> {
                    LOGGER.error("Error al buscar todos los vehiculos ", throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Vehiculos no encontrados").getMostSpecificCause()));
    }
}
