package com.dev.torhugo.domain.exception;
public abstract class DefaultException extends RuntimeException {
    private final String message;
    protected DefaultException(final String message) {
        this.message = message;
    }
    @Override
    public String getMessage(){
        return this.message;
    }
}
