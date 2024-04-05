package com.dev.torhugo.usecase;

import com.dev.torhugo.config.DefaultUseCase;
import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.ports.repository.AccountRepository;

public class UpdateLastAccessUseCase extends DefaultUseCase {
    private final AccountRepository accountRepository;

    public UpdateLastAccessUseCase(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void execute(final String email){
        logger.info("Executing use-case: UpdateLastAccess.");
        final var account = accountRepository.findByEmailWithThrow(email);
        final var actualAccount = Account.updateLastAccess(
                account.getAccountId(),
                account.getName(),
                account.getEmail(),
                account.getPassword(),
                account.isActive(),
                account.isAdmin(),
                account.getCreatedAt()
        );
        accountRepository.save(actualAccount);
    }
}
