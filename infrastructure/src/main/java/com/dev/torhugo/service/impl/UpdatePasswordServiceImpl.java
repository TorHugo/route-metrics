package com.dev.torhugo.service.impl;

import com.dev.torhugo.api.models.request.UpdatePasswordDTO;
import com.dev.torhugo.domain.exception.InvalidHashForgetPasswordException;
import com.dev.torhugo.dto.UcUpdatePasswordDTO;
import com.dev.torhugo.repository.ForgetPasswordRepository;
import com.dev.torhugo.service.UpdatePasswordService;
import com.dev.torhugo.usecase.UpdatePasswordUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdatePasswordServiceImpl implements UpdatePasswordService {
    private final UpdatePasswordUseCase updatePasswordUseCase;
    private final ForgetPasswordRepository forgetPasswordRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void updatePassword(final UpdatePasswordDTO request) {
        log.info("Executing service: UpdatePassword.");
        final var existsForgetPassword = forgetPasswordRepository.findByHashCodeIgnoreCaseAndActiveTrue(request.hash());
        if (Objects.isNull(existsForgetPassword))
            throw new InvalidHashForgetPasswordException("Hash not found!");
        final var input = new UcUpdatePasswordDTO(request.email(), passwordEncoder.encode(request.password()));
        final var accountId = updatePasswordUseCase.execute(input);
        final var findHash = forgetPasswordRepository.findByAccountIdAndActiveTrue(accountId);
        forgetPasswordRepository.updateActiveHash(findHash.getForgetPasswordId());
    }
}
