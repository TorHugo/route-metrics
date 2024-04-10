package com.dev.torhugo.application.usecase;

import com.dev.torhugo.application.config.DefaultUseCase;
import com.dev.torhugo.application.dto.UcSendPasswordDTO;
import com.dev.torhugo.application.ports.repository.AccountRepository;

import java.util.UUID;

public class SendPasswordUseCase extends DefaultUseCase {
    private final AccountRepository accountRepository;

    public SendPasswordUseCase(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public UUID execute(final UcSendPasswordDTO input){
        logger.info("Executing use-case: SendPassword().");
        final var account = accountRepository.findByEmailWithThrow(input.email());
        account.updatePassword(input.password());
        accountRepository.save(account);
        return account.getAccountId();
    }
}
