package com.dev.torhugo.application.usecase;

import com.dev.torhugo.application.config.DefaultUseCase;
import com.dev.torhugo.application.ports.repository.ForgetPasswordRepository;
import com.dev.torhugo.domain.entity.ForgetPassword;
import com.dev.torhugo.domain.exception.InvalidHashForgetPasswordException;

import java.util.Objects;
import java.util.UUID;

public class UpdateHashUseCase extends DefaultUseCase {
    private final ForgetPasswordRepository forgetPasswordRepository;

    public UpdateHashUseCase(final ForgetPasswordRepository forgetPasswordRepository) {
        this.forgetPasswordRepository = forgetPasswordRepository;
    }

    public void execute(final String hashcode,
                        final UUID accountId){
        logger.info("Executing use-case: UpdateHash().");
        final var existsForgetPassword = forgetPasswordRepository.findHashConfirmedTrue(hashcode, accountId);
        if (!existsForgetPassword.isConfirmed())
            throw new InvalidHashForgetPasswordException("HashCode is not confirmed!");
        final var actualForgetPassword = ForgetPassword.inactived(
                existsForgetPassword.getForgetPasswordId(),
                existsForgetPassword.getAccountId(),
                existsForgetPassword.getHashCode(),
                existsForgetPassword.isConfirmed(),
                existsForgetPassword.getExpirationDate(),
                existsForgetPassword.getCreatedAt()
        );
        forgetPasswordRepository.save(actualForgetPassword);
    }
}
