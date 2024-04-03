package com.dev.torhugo;

import com.dev.torhugo.config.DefaultUseCase;
import com.dev.torhugo.domain.error.exception.RepositoryNotFoundError;
import com.dev.torhugo.mapper.AccountMapper;
import com.dev.torhugo.models.BasicAccountDTO;
import com.dev.torhugo.repository.AccountRepository;

import java.util.Objects;
import java.util.UUID;

public class FindAccountUseCase extends DefaultUseCase {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    public FindAccountUseCase(final AccountRepository accountRepository,
                              final AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }
    public BasicAccountDTO execute(final UUID accountId){
        final var account = accountRepository.findByAccountId(accountId);
        if(Objects.isNull(account))
            throw new RepositoryNotFoundError("Account not found!");
        return accountMapper.mapperToBasic(account);
    }
}
