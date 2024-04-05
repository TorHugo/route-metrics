package com.dev.torhugo.configuration.usecase;

import com.dev.torhugo.ports.repository.AccountRepository;
import com.dev.torhugo.ports.repository.RouteRepository;
import com.dev.torhugo.usecase.CreateRouteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CreateRouteUseCaseConfig {
    private final RouteRepository routeRepository;
    private final AccountRepository accountRepository;
    @Bean
    public CreateRouteUseCase createRouteUseCase(){
        return new CreateRouteUseCase(accountRepository, routeRepository);
    }
}
