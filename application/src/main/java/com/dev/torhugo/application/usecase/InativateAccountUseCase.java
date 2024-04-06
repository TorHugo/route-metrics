package com.dev.torhugo.application.usecase;

import com.dev.torhugo.application.config.DefaultUseCase;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.dto.UcInativateAccountDTO;

public class InativateAccountUseCase extends DefaultUseCase {
    private final AccountRepository accountRepository;

    public InativateAccountUseCase(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void execute(final UcInativateAccountDTO input){
        logger.info("Executing use-case: InativateAccount.");
        final var actualAccounts = accountRepository.findAllByIds(input.accounts());
        final var inactiveAccounts =
                actualAccounts.stream().map(account ->
                        account.inactive(
                            account.getAccountId(),
                            account.getName(),
                            account.getEmail(),
                            account.getPassword(),
                            account.isAdmin(),
                            account.getCreatedAt()
                        )).toList();
        accountRepository.saveAll(inactiveAccounts);
    }
}
