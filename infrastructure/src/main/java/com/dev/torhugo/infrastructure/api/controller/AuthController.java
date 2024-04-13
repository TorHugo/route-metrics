package com.dev.torhugo.infrastructure.api.controller;

import com.dev.torhugo.infrastructure.api.AuthAPI;
import com.dev.torhugo.infrastructure.api.models.request.LoginDTO;
import com.dev.torhugo.infrastructure.security.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpHeaders.SET_COOKIE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthAPI {
    private final LoginService loginService;

    @Override
    public ResponseEntity<?> login(final LoginDTO request) {
        final var token = loginService.login(request);
        return ok().header(SET_COOKIE, token.toString()).body(null);
    }

    @Override
    public ResponseEntity<?> logout() {
        return ok().header(SET_COOKIE, loginService.logout().toString()).body(null);
    }
}
