package com.dev.torhugo.api;

import com.dev.torhugo.api.models.request.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/auth")
public interface AuthAPI {
    @PostMapping(
            "/login"
    )
    ResponseEntity<?> login(final @Valid @RequestBody LoginRequest request);
}
