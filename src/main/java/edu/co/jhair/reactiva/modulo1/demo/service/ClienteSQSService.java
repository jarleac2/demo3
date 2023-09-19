package edu.co.jhair.reactiva.modulo1.demo.service;

import com.amazonaws.services.arczonalshift.model.ListZonalShiftsRequest;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.*;
import edu.co.jhair.reactiva.modulo1.demo.model.Cliente;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
public class ClienteSQSService {
    private final AmazonSQS clienteSQS;

    public ClienteSQSService(AmazonSQS clienteSQS) {
        this.clienteSQS = clienteSQS;
    }

    public String createStandardQueue(String queueName){

        CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueName);
        return clienteSQS.createQueue(createQueueRequest).getQueueUrl();
    }

    private String getQueueUrl(String queueName){
        return clienteSQS.getQueueUrl(queueName).getQueueUrl();
    }

    public String publishStandardQueueMessage(String queueName, Integer delaySeconds, Cliente cliente){
        Map<String, MessageAttributeValue> atributosMensaje = new HashMap<>();

        atributosMensaje.put("id",
                new MessageAttributeValue()
                        .withStringValue(Optional.ofNullable(cliente.getId()).orElse(-301).toString())
                        .withDataType("Number"));
        atributosMensaje.put("idTipoDocumento",
                new MessageAttributeValue()
                        .withStringValue(Optional.ofNullable(cliente.getId()).orElse(-301).toString())
                        .withDataType("Number"));
        atributosMensaje.put("numeroIdentificacion",
                new MessageAttributeValue()
                        .withStringValue(cliente.getNumeroIdentificacion())
                        .withDataType("String"));
        atributosMensaje.put("nombre",
                new MessageAttributeValue()
                        .withStringValue(cliente.getNombre())
                        .withDataType("String"));
        atributosMensaje.put("apellido",
                new MessageAttributeValue()
                        .withStringValue(cliente.getApellido())
                        .withDataType("String"));
        atributosMensaje.put("telefono",
                new MessageAttributeValue()
                        .withStringValue(cliente.getTelefono())
                        .withDataType("String"));
        atributosMensaje.put("correo",
                new MessageAttributeValue()
                        .withStringValue(cliente.getCorreo())
                        .withDataType("String"));
        atributosMensaje.put("activo",
                new MessageAttributeValue()
                        .withStringValue(Optional.ofNullable(cliente.getActivo()).orElse(Boolean.FALSE).toString())
                        .withDataType("String"));

        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(this.getQueueUrl(queueName))
                .withMessageBody(cliente.getNombre())
                .withDelaySeconds(delaySeconds)
                .withMessageAttributes(atributosMensaje);

        return clienteSQS.sendMessage(sendMessageRequest).getMessageId();
    }

    public void publishStandardQueueMessage(String queueName, Integer delaySeconds, List<Cliente> clientes){
        for (Cliente cliente : clientes){
            publishStandardQueueMessage(queueName, delaySeconds, cliente);
        }
    }

    private List<Message> receiveMessagesFromQueue(String queueName, Integer maxNumberMessages, Integer waitTimeSeconds){
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(this.getQueueUrl(queueName))
                .withMaxNumberOfMessages(maxNumberMessages)
                .withMessageAttributeNames(List.of("All"))
                .withWaitTimeSeconds(waitTimeSeconds);
        return clienteSQS.receiveMessage(receiveMessageRequest).getMessages();
    }

    public Mono<Cliente> deleteClienteMessageInQueue(String queueName, Integer maxNumberMessages,
                                                     Integer waitTimeSeconds, String nombreCliente){
        List<Message> clienteMessages = receiveMessagesFromQueue(queueName, maxNumberMessages, waitTimeSeconds);
        for(Message message : clienteMessages){
            if(!message.getMessageAttributes().isEmpty()) {
                if (message.getMessageAttributes().get("nombre").getStringValue().equals(nombreCliente)) {
                    Cliente cliente = new Cliente(Integer.valueOf(message.getMessageAttributes().get("id").getStringValue()),
                            Integer.valueOf(message.getMessageAttributes().get("idTipoDocumento").getStringValue()),
                            message.getMessageAttributes().get("numeroIdentificacion").getStringValue(),
                            message.getMessageAttributes().get("nombre").getStringValue(),
                            message.getMessageAttributes().get("apellido").getStringValue(),
                            message.getMessageAttributes().get("telefono").getStringValue(),
                            message.getMessageAttributes().get("correo").getStringValue(),
                            Boolean.getBoolean(message.getMessageAttributes().get("activo").getStringValue()));
                    clienteSQS.deleteMessage(this.getQueueUrl(queueName), message.getReceiptHandle());
                    return Mono.just(cliente);
                }
            }
        }
        return Mono.empty();
    }

    public Flux<Cliente> getClienteMessage(String queueName, Integer maxNumberMessages,
                                           Integer waitTimeSeconds, String nombreCliente){
        List<Message> clienteMessages = receiveMessagesFromQueue(queueName, maxNumberMessages, waitTimeSeconds);
        List<Cliente> listaClientes = new ArrayList<Cliente>();
        for(Message message : clienteMessages){
            if(!message.getMessageAttributes().isEmpty()) {
                if (message.getMessageAttributes().get("nombre").getStringValue().equals(nombreCliente)) {
                    Cliente cliente = new Cliente(Integer.valueOf(message.getMessageAttributes().get("id").getStringValue()),
                            Integer.valueOf(message.getMessageAttributes().get("idTipoDocumento").getStringValue()),
                            message.getMessageAttributes().get("numeroIdentificacion").getStringValue(),
                            message.getMessageAttributes().get("nombre").getStringValue(),
                            message.getMessageAttributes().get("apellido").getStringValue(),
                            message.getMessageAttributes().get("telefono").getStringValue(),
                            message.getMessageAttributes().get("correo").getStringValue(),
                            Boolean.getBoolean(message.getMessageAttributes().get("activo").getStringValue()));

                    listaClientes.add(cliente);
                }
            }
        }
        return Flux.fromIterable(listaClientes);
    }
}
