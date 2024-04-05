package com.dev.torhugo.usecase;

import com.dev.torhugo.config.DefaultUseCase;
import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.dto.UcBasicAccountDTO;
import com.dev.torhugo.dto.UcUpdateAccountDTO;
import com.dev.torhugo.ports.repository.AccountRepository;

import static com.dev.torhugo.mappers.AccountMapper.mapperToBasic;
import static com.dev.torhugo.util.ValidateUtils.isNullOrEmpty;

public class UpdateAccountUseCase extends DefaultUseCase {
    private final AccountRepository accountRepository;

    public UpdateAccountUseCase(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public UcBasicAccountDTO execute(final UcUpdateAccountDTO input){
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
        return mapperToBasic(newAccount);
    }
}
