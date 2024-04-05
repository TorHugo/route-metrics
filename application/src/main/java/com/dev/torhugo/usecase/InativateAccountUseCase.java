package com.dev.torhugo.usecase;

import com.dev.torhugo.config.DefaultUseCase;
import com.dev.torhugo.dto.UcInativateAccountDTO;
import com.dev.torhugo.ports.repository.AccountRepository;

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
