package com.dev.torhugo.domain.error.exception;
import com.dev.torhugo.domain.error.DefaultError;

public class InvalidArgumentError extends DefaultError {
    public InvalidArgumentError(final String message) {
        super(message);
    }
}
