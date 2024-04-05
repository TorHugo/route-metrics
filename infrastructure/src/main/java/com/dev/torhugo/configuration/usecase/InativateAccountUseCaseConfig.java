package com.dev.torhugo.configuration.usecase;

import com.dev.torhugo.ports.repository.AccountRepository;
import com.dev.torhugo.usecase.InativateAccountUseCase;
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
