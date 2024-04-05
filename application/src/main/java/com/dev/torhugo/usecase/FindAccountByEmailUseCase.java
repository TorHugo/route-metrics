package com.dev.torhugo.usecase;

import com.dev.torhugo.config.DefaultUseCase;
import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.ports.repository.AccountRepository;

public class FindAccountByEmailUseCase extends DefaultUseCase {
    private final AccountRepository accountRepository;
    public FindAccountByEmailUseCase(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    public Account execute(final String email){
        return accountRepository.findByEmailWithThrow(email);
    }
}
