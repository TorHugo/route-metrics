package com.dev.torhugo.configuration.usecase;

import com.dev.torhugo.ports.repository.AccountRepository;
import com.dev.torhugo.usecase.UpdatePasswordUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UpdatePasswordUseCaseConfig {
    private final AccountRepository accountRepository;
    @Bean
    public UpdatePasswordUseCase updatePasswordUseCase(){
        return new UpdatePasswordUseCase(accountRepository);
    }
}
