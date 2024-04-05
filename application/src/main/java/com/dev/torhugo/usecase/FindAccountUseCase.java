package com.dev.torhugo.usecase;

import com.dev.torhugo.config.DefaultUseCase;
import com.dev.torhugo.mapper.AccountMapper;
import com.dev.torhugo.models.UcBasicAccountDTO;
import com.dev.torhugo.repository.AccountRepository;

public class FindAccountUseCase extends DefaultUseCase {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    public FindAccountUseCase(final AccountRepository accountRepository,
                              final AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }
    public UcBasicAccountDTO execute(final String email){
        final var account = accountRepository.findByEmailWithThrow(email);
        return accountMapper.mapperToBasic(account);
    }
}
