package com.dev.torhugo.service.impl;

import com.dev.torhugo.api.models.request.ConfirmHashDTO;
import com.dev.torhugo.domain.exception.InvalidHashForgetPasswordException;
import com.dev.torhugo.ports.repository.AccountRepository;
import com.dev.torhugo.repository.ForgetPasswordRepository;
import com.dev.torhugo.security.jwt.ForgetPasswordTokenUtils;
import com.dev.torhugo.service.ConfirmHashService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConfirmHashServiceImpl implements ConfirmHashService {

    private final ForgetPasswordRepository forgetPasswordRepository;
    private final AccountRepository accountRepository;
    private final ForgetPasswordTokenUtils tokenUtils;

    @Override
    public ResponseCookie confirmation(final ConfirmHashDTO request) {
        log.info("Executing service: ConfirmHash.");
        final var existsForgetPassword = forgetPasswordRepository.findByHashCodeIgnoreCaseAndActiveTrue(request.hash());
        if (Objects.isNull(existsForgetPassword))
            throw new InvalidHashForgetPasswordException("Hash not found!");
        final var dateNow = LocalDateTime.now();
        if (existsForgetPassword.getExpirationAt().isBefore(dateNow))
            throw new InvalidHashForgetPasswordException("This hash is expired!");
        final var account = accountRepository.findByAccountId(existsForgetPassword.getAccountId());
        return tokenUtils.generateForgetPasswordToken(account);
    }
}
