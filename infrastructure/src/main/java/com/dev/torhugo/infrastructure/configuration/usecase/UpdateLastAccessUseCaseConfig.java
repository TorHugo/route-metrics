package com.dev.torhugo.infrastructure.configuration.usecase;

import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.usecase.UpdateLastAccessUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UpdateLastAccessUseCaseConfig {
    private final AccountRepository accountRepository;
    @Bean
    public UpdateLastAccessUseCase updateLastAccessUseCase(){
        return new UpdateLastAccessUseCase(accountRepository);
    }
}
