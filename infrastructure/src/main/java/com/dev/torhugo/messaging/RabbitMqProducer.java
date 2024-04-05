package com.dev.torhugo.messaging;

import com.dev.torhugo.ports.messaging.QueueProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMqProducer implements QueueProducer {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void sendMessage(final String queue,
                            final Object message) {
        try {
            log.info("Sending message. Queue: {}", queue);
            final var convertedMessage = objectMapper.writeValueAsString(message);
            rabbitTemplate.convertAndSend(queue, convertedMessage);
        } catch (final JsonProcessingException e) {
            log.error("Error converting object. Exception: {}.", e.getMessage());
        }
    }
}
