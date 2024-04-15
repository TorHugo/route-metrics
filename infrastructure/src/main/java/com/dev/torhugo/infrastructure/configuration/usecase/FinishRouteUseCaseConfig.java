package com.dev.torhugo.infrastructure.configuration.usecase;

import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.ports.repository.RouteRepository;
import com.dev.torhugo.application.usecase.FinishRouteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FinishRouteUseCaseConfig {
    private final AccountRepository accountRepository;
    private final RouteRepository routeRepository;

    @Bean
    public FinishRouteUseCase finishRouteUseCase(){
        return new FinishRouteUseCase(accountRepository, routeRepository);
    }
}
