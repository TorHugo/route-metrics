package com.dev.torhugo.error.exception;

import lombok.Getter;

import java.time.Instant;

@Getter
public abstract class DefaultError {
    private final Instant timestamp;
    private final Integer status;
    private final String error;
    private final String message;
    private final String path;

    protected DefaultError(final Instant timestamp,
                           final Integer status,
                           final String error,
                           final String message,
                           final String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
