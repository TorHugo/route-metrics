package com.dev.torhugo.application.usecase;

import com.dev.torhugo.application.config.DefaultUseCase;
import com.dev.torhugo.application.ports.repository.ForgetPasswordRepository;
import com.dev.torhugo.domain.exception.InvalidHashForgetPasswordException;

import java.time.LocalDateTime;
import java.util.UUID;

public class ConfirmHashUseCase extends DefaultUseCase {
    private final ForgetPasswordRepository forgetPasswordRepository;

    public ConfirmHashUseCase(final ForgetPasswordRepository forgetPasswordRepository) {
        this.forgetPasswordRepository = forgetPasswordRepository;
    }

    public void execute(final String hascode,
                        final UUID accountId){
        logger.info("Executing use-case: ConfirmHash().");
        final var forgetPassword = forgetPasswordRepository.findHashConfirmedFalse(hascode, accountId);
        final var dateNow = LocalDateTime.now();
        if (forgetPassword.getExpirationDate().isBefore(dateNow))
            throw new InvalidHashForgetPasswordException("This hash is expired!");
        forgetPassword.confirmed();
        forgetPasswordRepository.save(forgetPassword);
    }
}
