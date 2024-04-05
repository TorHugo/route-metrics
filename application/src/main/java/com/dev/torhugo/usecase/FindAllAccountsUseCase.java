package com.dev.torhugo.usecase;

import com.dev.torhugo.config.DefaultUseCase;
import com.dev.torhugo.mapper.AccountMapper;
import com.dev.torhugo.models.UcBasicAccountDTO;
import com.dev.torhugo.repository.AccountRepository;

import java.util.List;

public class FindAllAccountsUseCase extends DefaultUseCase {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public FindAllAccountsUseCase(final AccountRepository accountRepository,
                                  final AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    public List<UcBasicAccountDTO> execute(){
        logger.info("Executing use-case: FindAllAccountsUseCase.");
        final var accounts = accountRepository.findAll();
        return accountMapper.mapperToListBasic(accounts);
    }
}
