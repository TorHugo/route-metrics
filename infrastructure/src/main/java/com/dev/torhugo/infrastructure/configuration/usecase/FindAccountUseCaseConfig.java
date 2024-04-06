package com.dev.torhugo.infrastructure.configuration.usecase;

import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.usecase.FindAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FindAccountUseCaseConfig {
    private final AccountRepository accountRepository;
    @Bean
    public FindAccountUseCase findAccountUseCase(){
        return new FindAccountUseCase(accountRepository);
    }
}
