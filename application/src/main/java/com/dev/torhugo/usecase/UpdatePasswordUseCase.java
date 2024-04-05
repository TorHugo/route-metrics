package com.dev.torhugo.usecase;

import com.dev.torhugo.config.DefaultUseCase;
import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.dto.UcUpdatePasswordDTO;
import com.dev.torhugo.ports.repository.AccountRepository;

import java.util.UUID;

public class UpdatePasswordUseCase extends DefaultUseCase {
    private final AccountRepository accountRepository;

    public UpdatePasswordUseCase(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public UUID execute(final UcUpdatePasswordDTO input){
        logger.info("Executing use-case: UpdatePassword.");
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
