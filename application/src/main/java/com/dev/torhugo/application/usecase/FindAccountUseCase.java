package com.dev.torhugo.application.usecase;

import com.dev.torhugo.application.config.DefaultUseCase;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.domain.entity.Account;

public class FindAccountUseCase extends DefaultUseCase {
    private final AccountRepository accountRepository;
    public FindAccountUseCase(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    public Account execute(final String email){
        return accountRepository.findByEmailWithThrow(email);
    }
}
