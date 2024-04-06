package com.dev.torhugo.infrastructure.exception.dto;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class BadRequestDTO extends DefaultError {
    protected BadRequestDTO(final LocalDateTime timestamp,
                            final Integer status,
                            final String error,
                            final String message,
                            final String path) {
        super(timestamp, status, error, message, path);
    }

    public static BadRequestDTO fromException(final Exception ex,
                                              final HttpServletRequest request){
        return new BadRequestDTO(
                LocalDateTime.now(),
                BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
    }

}
