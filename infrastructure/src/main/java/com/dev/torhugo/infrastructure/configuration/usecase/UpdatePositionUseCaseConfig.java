package com.dev.torhugo.infrastructure.configuration.usecase;

import com.dev.torhugo.application.ports.repository.PositionRepository;
import com.dev.torhugo.application.ports.repository.RouteRepository;
import com.dev.torhugo.application.usecase.UpdatePositionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UpdatePositionUseCaseConfig {
    private final PositionRepository positionRepository;
    private final RouteRepository routeRepository;

    @Bean
    public UpdatePositionUseCase updatePositionUseCase(){
        return new UpdatePositionUseCase(positionRepository, routeRepository);
    }
}
