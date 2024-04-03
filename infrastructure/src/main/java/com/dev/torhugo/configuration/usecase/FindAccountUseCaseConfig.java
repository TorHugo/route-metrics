package com.dev.torhugo.configuration.usecase;

import com.dev.torhugo.usecase.FindAccountUseCase;
import com.dev.torhugo.mapper.AccountMapper;
import com.dev.torhugo.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FindAccountUseCaseConfig {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    @Bean
    public FindAccountUseCase findAccountUseCase(){
        return new FindAccountUseCase(accountRepository, accountMapper);
    }
}
