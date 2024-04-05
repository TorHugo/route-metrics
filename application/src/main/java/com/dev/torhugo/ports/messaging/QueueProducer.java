package com.dev.torhugo.ports.messaging;

public interface QueueProducer {
    void sendMessage(final String queue,
                     final Object message);
}
