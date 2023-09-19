package edu.co.jhair.reactiva.modulo1.demo.controller;

import ch.qos.logback.core.net.server.Client;
import edu.co.jhair.reactiva.modulo1.demo.dto.DTOClienteNombreActivo;
import edu.co.jhair.reactiva.modulo1.demo.model.Cliente;
import edu.co.jhair.reactiva.modulo1.demo.service.ClienteKafkaConsumerService;
import edu.co.jhair.reactiva.modulo1.demo.service.ClienteSQSService;
import edu.co.jhair.reactiva.modulo1.demo.service.ClienteService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    ClienteService clienteService;

    ClienteKafkaConsumerService clienteKafkaConsumerService;
    ClienteSQSService clienteSQSService;

    public ClienteController(ClienteService clienteService,
                             ClienteKafkaConsumerService clienteKafkaConsumerService,
                             ClienteSQSService clienteSQSService) {
        this.clienteService = clienteService;
        this.clienteKafkaConsumerService = clienteKafkaConsumerService;
        this.clienteSQSService = clienteSQSService;
    }

    @GetMapping("/{id}")
    public Mono<Cliente> getClienteById(@PathVariable Integer id){
        return clienteService.findById(id);
    }

    @GetMapping("/")
    public Flux<Cliente> getAllClientes(){
        return clienteService.findAll();
    }
    @GetMapping("/{nombre}")
    public Flux<Cliente> getClientesByNombre(@PathVariable String nombre){
        return clienteService.findByNombre(nombre);
    }
    @GetMapping("/{id}/{nombre}")
    public Flux<Cliente> getClientesByActivoByNombre(@PathVariable String nombres, @PathVariable Boolean activo){
        return clienteService.findByNombreActivo(nombres, activo);
    }

    @PostMapping("/")
    public Mono<Cliente> createCliente(@RequestBody Cliente cliente){
        return clienteService.save(cliente);
    }

    @PutMapping("/")
    public Mono<Cliente> updateCliente(@PathVariable Integer id, @RequestBody Cliente cliente){
        return clienteService.update(id, cliente);
    }

    @DeleteMapping("/{id}")
    public Mono<Cliente> deleteClienteById(@PathVariable Integer id){
        return clienteService.deleteById(id);
    }

    @DeleteMapping("/")
    public Mono<Void> deleteAllClientes(){
        return clienteService.deleteAll();
    }

    @PostMapping("/cliente-por-activo-por-descripcion")
    public Flux<Cliente> getClientesByActivoByDescripcion(@RequestBody DTOClienteNombreActivo dtoClienteNombreActivo){
        return clienteService.findByNombreActivo(dtoClienteNombreActivo.nombre(),dtoClienteNombreActivo.activo());
    }

    @GetMapping("/topico-kakfa/{topico}")
    public Mono<String> getClienteFromTopicoKafka(@PathVariable String topico) {
        return Mono.just(clienteKafkaConsumerService.obtenerUltimoCliente(topico));
    }

    @PostMapping("/aws/createQueue")
    public Mono<String> postCreateQueue(@RequestBody Map<String, Object> requestBody){
        return Mono.just(clienteSQSService.createStandardQueue((String) requestBody.get("queueName")));
    }

    @PostMapping("/aws/postMessageQueue/{queueName}")
    public Mono<String> postMessageQueue(@RequestBody Cliente cliente, @PathVariable String queueName){
        return Mono.just(clienteSQSService.publishStandardQueueMessage(
                queueName,
                2,
                cliente));
    }

    @PostMapping("/aws/processClienteByNombre")
    public Mono<Cliente> deleteClienteFromQueueByNombre(@RequestBody Map<String, Object> requestBody){
        return clienteSQSService.deleteClienteMessageInQueue((String) requestBody.get("queueName"),
                (Integer) requestBody.get("maxNumberMessages"),
                (Integer) requestBody.get("waitTimeSeconds"),
                (String) requestBody.get("nombreCliente"));
    }

    @GetMapping("/aws/getMessagesByNombre")
    public Flux<Cliente> getMessagesByNombre(@RequestBody Map<String, Object> requestBody){
        return clienteSQSService.getClienteMessage((String) requestBody.get("queueName"),
                (Integer) requestBody.get("maxNumberMessages"),
                (Integer) requestBody.get("waitTimeSeconds"),
                (String) requestBody.get("nombreCliente"));
    }

}
