package com.dev.torhugo.infrastructure.configuration.usecase;

import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.ports.repository.PositionRepository;
import com.dev.torhugo.application.ports.repository.RouteRepository;
import com.dev.torhugo.application.usecase.FindDetailsRouteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FindDetailsRouteUseCaseConfig {
    private final AccountRepository accountRepository;
    private final RouteRepository routeRepository;
    private final PositionRepository positionRepository;

    @Bean
    public FindDetailsRouteUseCase findDetailsRouteUseCase(){
        return new FindDetailsRouteUseCase(accountRepository, routeRepository, positionRepository);
    }
}
