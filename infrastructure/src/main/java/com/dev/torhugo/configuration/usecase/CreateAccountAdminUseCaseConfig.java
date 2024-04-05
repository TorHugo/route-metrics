package com.dev.torhugo.configuration.usecase;

import com.dev.torhugo.repository.AccountRepository;
import com.dev.torhugo.usecase.CreateAccountAdminUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CreateAccountAdminUseCaseConfig {
    private final AccountRepository accountRepository;
    @Bean
    public CreateAccountAdminUseCase createAccountAdminUseCase(){
        return new CreateAccountAdminUseCase(accountRepository);
    }
}
