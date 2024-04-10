package com.dev.torhugo.application.usecase;

import com.dev.torhugo.application.config.DefaultUseCase;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.dto.UcInativateAccountDTO;
import com.dev.torhugo.domain.entity.Account;

public class InativateAccountUseCase extends DefaultUseCase {
    private final AccountRepository accountRepository;

    public InativateAccountUseCase(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void execute(final UcInativateAccountDTO input){
        logger.info("Executing use-case: InativateAccount.");
        final var actualAccounts = accountRepository.findAllByIds(input.accounts());
        actualAccounts.forEach(Account::inactive);
        accountRepository.saveAll(actualAccounts);
    }
}
