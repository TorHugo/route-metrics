package com.dev.torhugo.configuration.usecase;

import com.dev.torhugo.ports.repository.RouteRepository;
import com.dev.torhugo.usecase.FindRouteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FindRouteUseCaseConfig {
    private final RouteRepository routeRepository;
    @Bean
    public FindRouteUseCase findRouteUseCase(){
        return new FindRouteUseCase(routeRepository);
    }
}
