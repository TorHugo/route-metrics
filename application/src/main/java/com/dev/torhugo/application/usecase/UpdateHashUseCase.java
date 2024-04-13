package com.dev.torhugo.application.usecase;

import com.dev.torhugo.application.config.DefaultUseCase;
import com.dev.torhugo.application.ports.repository.ForgetPasswordRepository;

import java.util.UUID;

public class UpdateHashUseCase extends DefaultUseCase {
    private final ForgetPasswordRepository forgetPasswordRepository;

    public UpdateHashUseCase(final ForgetPasswordRepository forgetPasswordRepository) {
        this.forgetPasswordRepository = forgetPasswordRepository;
    }

    public void execute(final String hashcode,
                        final UUID accountId){
        logger.info("Executing use-case: UpdateHash().");
        final var forgetPassword = forgetPasswordRepository.findHashConfirmedTrue(hashcode, accountId);
        forgetPassword.inactived();
        forgetPasswordRepository.save(forgetPassword);
    }
}
