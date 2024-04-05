package com.dev.torhugo.domain.exception;

public class InvalidHashForgetPasswordException extends DefaultException {
    public InvalidHashForgetPasswordException(final String message) {
        super(message);
    }
}
