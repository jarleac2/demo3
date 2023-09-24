package edu.co.jhair.reactiva.modulo1.demo.controller.v2;

import edu.co.jhair.reactiva.modulo1.demo.model.Vehiculo;
import edu.co.jhair.reactiva.modulo1.demo.service.v2.VehiculoService2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/v2/vehiculo")
public class VehiculoController2 {

    VehiculoService2 vehiculoService;

    public VehiculoController2(VehiculoService2 vehiculoService){
        this.vehiculoService = vehiculoService;
    }

    @GetMapping("/")
    public Flux<Vehiculo> getTodosVehiculos() {
        return vehiculoService.findTodos();
    }
}
