package com.dev.torhugo.configuration.usecase;

import com.dev.torhugo.CreateAccountUseCase;
import com.dev.torhugo.messaging.QueueProducer;
import com.dev.torhugo.repository.AccountRepository;
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
