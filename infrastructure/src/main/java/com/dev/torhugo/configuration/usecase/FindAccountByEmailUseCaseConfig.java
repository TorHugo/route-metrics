package com.dev.torhugo.configuration.usecase;

import com.dev.torhugo.ports.repository.AccountRepository;
import com.dev.torhugo.usecase.FindAccountByEmailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FindAccountByEmailUseCaseConfig {
    private final AccountRepository accountRepository;
    @Bean
    public FindAccountByEmailUseCase findAccountByEmailUseCase(){
        return new FindAccountByEmailUseCase(accountRepository);
    }
}
