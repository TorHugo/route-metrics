package com.dev.torhugo.application.usecase;

import com.dev.torhugo.application.config.DefaultUseCase;
import com.dev.torhugo.application.dto.UcUpdateAccountDTO;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.domain.entity.Account;

import static com.dev.torhugo.application.util.ValidateUtils.isNullOrEmpty;

public class UpdateAccountUseCase extends DefaultUseCase {
    private final AccountRepository accountRepository;

    public UpdateAccountUseCase(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account execute(final UcUpdateAccountDTO input){
        logger.info("Executing use-case: UpdateAccount.");
        final var actualAccount = accountRepository.findByAccountId(input.accountId());
        final var newAccount = Account.update(
            actualAccount.getAccountId(),
                isNullOrEmpty(input.name()) ? actualAccount.getName() : input.name(),
                isNullOrEmpty(input.email()) ? actualAccount.getEmail() : input.email(),
                isNullOrEmpty(input.password()) ? actualAccount.getPassword() : input.password(),
                input.active(),
                input.admin(),
                actualAccount.getLastAccess(),
                actualAccount.getCreatedAt()
        );
        accountRepository.save(newAccount);
        return newAccount;
    }
}
