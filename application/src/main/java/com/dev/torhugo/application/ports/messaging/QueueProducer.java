package com.dev.torhugo.application.ports.messaging;

public interface QueueProducer {
    void sendMessage(final String queue,
                     final Object message);
}
