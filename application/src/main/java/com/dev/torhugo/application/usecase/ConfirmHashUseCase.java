package com.dev.torhugo.application.usecase;

import com.dev.torhugo.application.config.DefaultUseCase;
import com.dev.torhugo.application.ports.repository.ForgetPasswordRepository;
import com.dev.torhugo.domain.entity.ForgetPassword;
import com.dev.torhugo.domain.exception.InvalidHashForgetPasswordException;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class ConfirmHashUseCase extends DefaultUseCase {
    private final ForgetPasswordRepository forgetPasswordRepository;

    public ConfirmHashUseCase(final ForgetPasswordRepository forgetPasswordRepository) {
        this.forgetPasswordRepository = forgetPasswordRepository;
    }

    public void execute(final String hascode,
                        final UUID accountId){
        logger.info("Executing use-case: ConfirmHash().");
        final var existingHashCode = forgetPasswordRepository.findHashConfirmedFalse(hascode, accountId);
        if (Objects.isNull(existingHashCode))
            throw new InvalidHashForgetPasswordException("Hash not found!");
        final var dateNow = LocalDateTime.now();
        if (existingHashCode.getExpirationDate().isBefore(dateNow))
            throw new InvalidHashForgetPasswordException("This hash is expired!");
        final var forgetPassword = ForgetPassword.confirmed(
                existingHashCode.getForgetPasswordId(),
                existingHashCode.getAccountId(),
                existingHashCode.getHashCode(),
                existingHashCode.isActive(),
                existingHashCode.getExpirationDate(),
                existingHashCode.getCreatedAt()
        );
        forgetPasswordRepository.save(forgetPassword);
    }
}
