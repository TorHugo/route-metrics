package com.dev.torhugo.application.usecase;

import com.dev.torhugo.application.config.DefaultUseCase;
import com.dev.torhugo.application.dto.UcUpdatePasswordDTO;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.domain.exception.InvalidArgumentException;

public class UpdatePasswordUseCase extends DefaultUseCase {
    private final AccountRepository accountRepository;

    public UpdatePasswordUseCase(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void execute(final Account actualAccount,
                        final UcUpdatePasswordDTO input){
        logger.info("Executing use-case: UpdatePassword()");
        if (!input.comparingPassword())
            throw new InvalidArgumentException("The password sent is not the same as the current password!");
        final var newAccount = Account.update(
                actualAccount.getAccountId(),
                actualAccount.getName(),
                actualAccount.getEmail(),
                input.newPassword(),
                actualAccount.isActive(),
                actualAccount.isAdmin(),
                actualAccount.getLastAccess(),
                actualAccount.getCreatedAt()
        );
        accountRepository.save(newAccount);
    }
}
