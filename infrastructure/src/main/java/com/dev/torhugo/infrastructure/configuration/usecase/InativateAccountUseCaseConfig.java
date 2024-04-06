package com.dev.torhugo.infrastructure.configuration.usecase;

import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.usecase.InativateAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class InativateAccountUseCaseConfig {
    private final AccountRepository accountRepository;
    @Bean
    public InativateAccountUseCase inativateAccountUseCase(){
        return new InativateAccountUseCase(accountRepository);
    }
}
