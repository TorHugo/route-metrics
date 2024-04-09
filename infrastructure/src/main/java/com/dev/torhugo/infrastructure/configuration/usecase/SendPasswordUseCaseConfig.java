package com.dev.torhugo.infrastructure.configuration.usecase;

import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.usecase.SendPasswordUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SendPasswordUseCaseConfig {
    private final AccountRepository accountRepository;
    @Bean
    public SendPasswordUseCase sendPasswordUseCase(){
        return new SendPasswordUseCase(accountRepository);
    }
}
