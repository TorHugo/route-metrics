package com.dev.torhugo.configuration.usecase;

import com.dev.torhugo.repository.AccountRepository;
import com.dev.torhugo.usecase.UpdateLastAccessUseCase;
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
