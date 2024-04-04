package com.dev.torhugo.api.controller;

import com.dev.torhugo.api.AuthAPI;
import com.dev.torhugo.api.models.request.BasicAccountRequest;
import com.dev.torhugo.api.models.request.LoginRequest;
import com.dev.torhugo.api.models.response.AccountCreateResponse;
import com.dev.torhugo.models.AccountDTO;
import com.dev.torhugo.security.service.LoginService;
import com.dev.torhugo.usecase.CreateAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpHeaders.SET_COOKIE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthAPI {
    private final CreateAccountUseCase createAccountUseCase;
    private final LoginService loginService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public ResponseEntity<?> register(final BasicAccountRequest request) {
        final var input = new AccountDTO(
                request.name(),
                request.email(),
                passwordEncoder.encode(request.password())
        );

        final var result = new AccountCreateResponse(createAccountUseCase.execute(input));
        return ok().body(result);
    }

    @Override
    public ResponseEntity<?> login(final LoginRequest request) {
        final var token = loginService.login(request);
        return ok().header(SET_COOKIE, token.toString()).body(null);
    }
}
