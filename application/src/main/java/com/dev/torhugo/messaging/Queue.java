package com.dev.torhugo.messaging;

public enum Queue {
    WELCOME("QUEUE_WELCOME_ACCOUNT");
    Queue(final String name) {
        this.name = name;
    }
    private final String name;
    public String getName() {
        return name;
    }
}
