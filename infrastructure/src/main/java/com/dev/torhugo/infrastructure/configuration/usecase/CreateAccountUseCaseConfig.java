package com.dev.torhugo.infrastructure.configuration.usecase;

import com.dev.torhugo.application.usecase.CreateAccountUseCase;
import com.dev.torhugo.application.ports.messaging.QueueProducer;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CreateAccountUseCaseConfig {
    private final AccountRepository accountRepository;
    private final QueueProducer queueProducer;
    @Bean
    public CreateAccountUseCase createAccountUseCase(){
        return new CreateAccountUseCase(accountRepository, queueProducer);
    }
}
