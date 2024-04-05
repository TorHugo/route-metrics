package com.dev.torhugo.configuration.usecase;

import com.dev.torhugo.ports.repository.AccountRepository;
import com.dev.torhugo.usecase.UpdateAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UpdateAccountUseCaseConfig {
    private final AccountRepository accountRepository;
    @Bean
    public UpdateAccountUseCase updateAccountUseCase(){
        return new UpdateAccountUseCase(accountRepository);
    }
}
