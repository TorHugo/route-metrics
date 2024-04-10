package com.dev.torhugo.infrastructure.api.controller;

import com.dev.torhugo.application.dto.UcSendPasswordDTO;
import com.dev.torhugo.application.usecase.*;
import com.dev.torhugo.infrastructure.api.ForgetPasswordAPI;
import com.dev.torhugo.infrastructure.api.models.request.ConfirmHashDTO;
import com.dev.torhugo.infrastructure.api.models.request.ForgetPasswordDTO;
import com.dev.torhugo.infrastructure.api.models.request.SendPasswordDTO;
import com.dev.torhugo.infrastructure.security.jwt.TokenUtils;
import com.dev.torhugo.infrastructure.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpHeaders.SET_COOKIE;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@RestController
@RequiredArgsConstructor
public class ForgetPasswordController implements ForgetPasswordAPI {
    private final FindAccountUseCase findAccountUseCase;
    private final SendHashUseCase sendHashUseCase;
    private final ConfirmHashUseCase confirmHashUseCase;
    private final SendPasswordUseCase sendPasswordUseCase;
    private final UpdateHashUseCase updateHashUseCase;
    private final TokenUtils tokenUtils;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void forgetPassword(final ForgetPasswordDTO request) {
        sendHashUseCase.execute(request.email());
    }

    @Override
    public ResponseEntity<?> confirmHashCode(final ConfirmHashDTO request) {
        final var account = findAccountUseCase.execute(request.email());
        confirmHashUseCase.execute(request.hash(), account.getAccountId());
        final var userDetails = UserDetailsImpl.build(account, createAuthorityList("ROLE_USER"));
        final var token = tokenUtils.generateToken(userDetails, true);
        return ok().header(SET_COOKIE, token.toString()).body(null);
    }

    @Override
    public void updatePassword(final SendPasswordDTO request) {
        final var account = findAccountUseCase.execute(request.email());
        final var newPassword = passwordEncoder.encode(request.password());
        final var input = new UcSendPasswordDTO(request.email(), newPassword);
        updateHashUseCase.execute(request.hash(), account.getAccountId());
        sendPasswordUseCase.execute(input);
    }
}
