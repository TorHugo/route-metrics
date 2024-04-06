package com.dev.torhugo.application.usecase;

import com.dev.torhugo.application.config.DefaultUseCase;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.dto.UcBasicAccountDTO;

import static com.dev.torhugo.application.mappers.AccountMapper.mapperToBasic;

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
