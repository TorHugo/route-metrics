package com.dev.torhugo.infrastructure.configuration.usecase;

import com.dev.torhugo.application.ports.repository.ForgetPasswordRepository;
import com.dev.torhugo.application.usecase.UpdateHashUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UpdateHashUseCaseConfig {
    private final ForgetPasswordRepository forgetPasswordRepository;
    @Bean
    public UpdateHashUseCase updateHashUseCase(){
        return new UpdateHashUseCase(forgetPasswordRepository);
    }
}
