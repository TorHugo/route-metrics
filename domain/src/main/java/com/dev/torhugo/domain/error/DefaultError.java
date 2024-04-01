package com.dev.torhugo.domain.error;
public abstract class DefaultError extends RuntimeException {
    private final String message;
    protected DefaultError(final String message) {
        this.message = message;
    }
    @Override
    public String getMessage(){
        return this.message;
    }
}
