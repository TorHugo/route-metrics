package com.dev.torhugo.infrastructure.exception;

import com.dev.torhugo.domain.exception.InvalidHashForgetPasswordException;
import com.dev.torhugo.domain.exception.InvalidArgumentException;
import com.dev.torhugo.domain.exception.RepositoryException;
import com.dev.torhugo.infrastructure.exception.dto.BadRequestDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<Object> handleInvalidArgumentError(final InvalidArgumentException ex,
                                                             final HttpServletRequest request) {
        final var body = BadRequestDTO.fromException(ex, request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(RepositoryException.class)
    public ResponseEntity<Object> handleInvalidArgumentError(final RepositoryException ex,
                                                             final HttpServletRequest request) {
        final var body = BadRequestDTO.fromException(ex, request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(InvalidHashForgetPasswordException.class)
    public ResponseEntity<Object> handleInvalidHashForgetPassword(final InvalidHashForgetPasswordException ex,
                                                                  final HttpServletRequest request) {
        final var body = BadRequestDTO.fromException(ex, request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
