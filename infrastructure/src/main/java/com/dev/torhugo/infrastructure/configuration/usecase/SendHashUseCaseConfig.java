package com.dev.torhugo.infrastructure.configuration.usecase;

import com.dev.torhugo.application.ports.messaging.QueueProducer;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.ports.repository.ForgetPasswordRepository;
import com.dev.torhugo.application.usecase.SendHashUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SendHashUseCaseConfig {
    private final AccountRepository accountRepository;
    private final ForgetPasswordRepository forgetPasswordRepository;
    private final QueueProducer queueProducer;
    @Bean
    public SendHashUseCase sendHashUseCase(){
        return new SendHashUseCase(accountRepository, forgetPasswordRepository, queueProducer);
    }
}
