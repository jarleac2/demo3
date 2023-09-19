package edu.co.jhair.reactiva.modulo1.demo.service;


import edu.co.jhair.reactiva.modulo1.demo.config.KafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClienteKafkaConsumerService {
    private final Logger LOGGER = LoggerFactory.getLogger(ClienteKafkaConsumerService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ClienteKafkaConsumerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String obtenerUltimoCliente(String topico) {
        ConsumerRecord<String, String> ultimoCliente;
        KafkaConfig kafkaConfig = new KafkaConfig();
        kafkaTemplate.setConsumerFactory(kafkaConfig.consumerFactory());
        ultimoCliente = kafkaTemplate.receive(topico, 0, 0);
        String clienteRecibido = Objects.requireNonNull(ultimoCliente.value());
        return clienteRecibido;
    }
}
