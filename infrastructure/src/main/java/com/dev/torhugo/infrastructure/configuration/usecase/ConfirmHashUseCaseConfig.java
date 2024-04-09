package com.dev.torhugo.infrastructure.configuration.usecase;

import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.ports.repository.ForgetPasswordRepository;
import com.dev.torhugo.application.usecase.ConfirmHashUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ConfirmHashUseCaseConfig {
    private final ForgetPasswordRepository forgetPasswordRepository;
    @Bean
    public ConfirmHashUseCase confirmHashUseCase(){
        return new ConfirmHashUseCase(forgetPasswordRepository);
    }
}
