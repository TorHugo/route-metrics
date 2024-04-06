package com.dev.torhugo.infrastructure.configuration.usecase;

import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.usecase.FindAllAccountsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FindAllAccountsUseCaseConfig {
    private final AccountRepository accountRepository;
    @Bean
    public FindAllAccountsUseCase findAllAccountsUseCase(){
        return new FindAllAccountsUseCase(accountRepository);
    }
}
