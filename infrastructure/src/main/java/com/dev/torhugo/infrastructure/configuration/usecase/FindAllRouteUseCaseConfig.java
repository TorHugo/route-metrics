package com.dev.torhugo.infrastructure.configuration.usecase;

import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.ports.repository.RouteRepository;
import com.dev.torhugo.application.usecase.FindAllRouteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FindAllRouteUseCaseConfig {
    private final RouteRepository routeRepository;
    private final AccountRepository accountRepository;
    @Bean
    public FindAllRouteUseCase findAllRouteUseCase(){
        return new FindAllRouteUseCase(routeRepository, accountRepository);
    }
}
