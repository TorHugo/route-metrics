package com.dev.torhugo.usecase;

import com.dev.torhugo.config.DefaultUseCase;
import com.dev.torhugo.dtos.UcBasicAccountDTO;
import com.dev.torhugo.ports.repository.AccountRepository;

import static com.dev.torhugo.mappers.AccountMapper.mapperToBasic;

public class FindAccountUseCase extends DefaultUseCase {
    private final AccountRepository accountRepository;
    public FindAccountUseCase(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    public UcBasicAccountDTO execute(final String email){
        final var account = accountRepository.findByEmailWithThrow(email);
        return mapperToBasic(account);
    }
}
