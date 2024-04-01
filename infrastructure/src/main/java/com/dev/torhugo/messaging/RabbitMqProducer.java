package com.dev.torhugo.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqProducer implements QueueProducer {
    private final RabbitTemplate rabbitTemplate;
    public RabbitMqProducer(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(final String queue,
                            final Object message) {
        rabbitTemplate.convertAndSend(queue, message);
    }
}
