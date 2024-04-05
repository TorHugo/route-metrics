package com.dev.torhugo.usecase;

import com.dev.torhugo.config.DefaultUseCase;
import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.domain.error.exception.InvalidArgumentError;
import com.dev.torhugo.dtos.UcAccountAdminDTO;
import com.dev.torhugo.ports.repository.AccountRepository;

import java.util.Objects;
import java.util.UUID;

public class CreateAccountAdminUseCase extends DefaultUseCase {
    private final AccountRepository accountRepository;

    public CreateAccountAdminUseCase(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public UUID execute(final UcAccountAdminDTO input){
        logger.info("Executing use-case: CreateAccountAdmin.");
        final var existisAccount = accountRepository.findByEmail(input.email());
        if(Objects.nonNull(existisAccount))
            throw new InvalidArgumentError("Account already exists!");
        final var account = Account.create(
                input.name(),
                input.email(),
                input.password(),
                input.active(),
                input.admin()
        );
        accountRepository.save(account);
        return account.getAccountId();
    }
}
