package com.dev.torhugo.application.usecase;

import com.dev.torhugo.application.config.DefaultUseCase;
import com.dev.torhugo.application.ports.repository.AccountRepository;

public class UpdateLastAccessUseCase extends DefaultUseCase {
    private final AccountRepository accountRepository;

    public UpdateLastAccessUseCase(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void execute(final String email){
        logger.info("Executing use-case: UpdateLastAccess.");
        final var account = accountRepository.findByEmailWithThrow(email);
        account.lastAccess();
        accountRepository.save(account);
    }
}
