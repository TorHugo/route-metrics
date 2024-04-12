package com.dev.torhugo.infrastructure.configuration.usecase;

import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.ports.repository.PositionRepository;
import com.dev.torhugo.application.ports.repository.RouteRepository;
import com.dev.torhugo.application.usecase.RequestRouteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CreateRouteUseCaseConfig {
    private final RouteRepository routeRepository;
    private final PositionRepository positionRepository;
    private final AccountRepository accountRepository;
    @Bean
    public RequestRouteUseCase requestRouteUseCase(){
        return new RequestRouteUseCase(accountRepository, routeRepository, positionRepository);
    }
}
