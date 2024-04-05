package com.dev.torhugo.usecase;

import com.dev.torhugo.config.DefaultUseCase;
import com.dev.torhugo.dtos.UcBasicAccountDTO;
import com.dev.torhugo.ports.repository.AccountRepository;

import java.util.List;

import static com.dev.torhugo.mappers.AccountMapper.mapperToListBasic;

public class FindAllAccountsUseCase extends DefaultUseCase {
    private final AccountRepository accountRepository;

    public FindAllAccountsUseCase(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<UcBasicAccountDTO> execute(){
        logger.info("Executing use-case: FindAllAccountsUseCase.");
        final var accounts = accountRepository.findAll();
        return mapperToListBasic(accounts);
    }
}
