package com.dev.torhugo.domain.error.exception;
import com.dev.torhugo.domain.error.DefaultError;

public class RepositoryNotFoundError extends DefaultError {
    public RepositoryNotFoundError(final String message) {
        super(message);
    }
}
