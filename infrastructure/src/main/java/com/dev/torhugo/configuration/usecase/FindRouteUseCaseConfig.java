package com.dev.torhugo.configuration.usecase;

import com.dev.torhugo.mapper.RouteMapper;
import com.dev.torhugo.repository.AccountRepository;
import com.dev.torhugo.repository.RouteRepository;
import com.dev.torhugo.usecase.CreateRouteUseCase;
import com.dev.torhugo.usecase.FindRouteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FindRouteUseCaseConfig {
    private final RouteRepository routeRepository;
    private final RouteMapper routeMapper;
    @Bean
    public FindRouteUseCase findRouteUseCase(){
        return new FindRouteUseCase(routeRepository, routeMapper);
    }
}
