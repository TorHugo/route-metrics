package com.dev.torhugo.messaging;

public interface QueueProducer {
    void sendMessage(final String queue,
                     final Object message);
}
