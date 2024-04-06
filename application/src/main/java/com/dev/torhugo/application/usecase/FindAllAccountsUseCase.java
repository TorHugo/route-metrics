package com.dev.torhugo.application.usecase;

import com.dev.torhugo.application.config.DefaultUseCase;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.dto.UcBasicAccountDTO;

import java.util.List;

import static com.dev.torhugo.application.mappers.AccountMapper.mapperToListBasic;

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
