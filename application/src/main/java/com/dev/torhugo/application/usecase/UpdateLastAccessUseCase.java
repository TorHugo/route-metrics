package com.dev.torhugo.application.usecase;

import com.dev.torhugo.application.config.DefaultUseCase;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.domain.entity.Account;

import java.time.LocalDateTime;

public class UpdateLastAccessUseCase extends DefaultUseCase {
    private final AccountRepository accountRepository;

    public UpdateLastAccessUseCase(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void execute(final String email){
        logger.info("Executing use-case: UpdateLastAccess.");
        final var account = accountRepository.findByEmailWithThrow(email);
        final var dateNow = LocalDateTime.now();
        final var actualAccount = Account.update(
                account.getAccountId(),
                account.getName(),
                account.getEmail(),
                account.getPassword(),
                account.isActive(),
                account.isAdmin(),
                dateNow,
                account.getCreatedAt()
        );
        accountRepository.save(actualAccount);
    }
}
