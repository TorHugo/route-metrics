package com.dev.torhugo.application.usecase;

import com.dev.torhugo.application.config.DefaultUseCase;
import com.dev.torhugo.application.dto.UcSendPasswordDTO;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.domain.entity.Account;

import java.util.UUID;

public class SendPasswordUseCase extends DefaultUseCase {
    private final AccountRepository accountRepository;

    public SendPasswordUseCase(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public UUID execute(final UcSendPasswordDTO input){
        logger.info("Executing use-case: SendPassword().");
        final var account = accountRepository.findByEmailWithThrow(input.email());
        final var updatedAccount = Account.update(
                account.getAccountId(),
                account.getName(),
                account.getEmail(),
                input.password(),
                account.isActive(),
                account.isAdmin(),
                account.getCreatedAt(),
                account.getLastAccess()
        );
        accountRepository.save(updatedAccount);
        return account.getAccountId();
    }
}
