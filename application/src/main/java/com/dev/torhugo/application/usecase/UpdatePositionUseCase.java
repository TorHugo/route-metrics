package com.dev.torhugo.application.usecase;

import com.dev.torhugo.application.config.DefaultUseCase;
import com.dev.torhugo.application.dto.UcBasicPositionDTO;
import com.dev.torhugo.application.dto.UcUpdatePositionDTO;
import com.dev.torhugo.application.ports.repository.PositionRepository;
import com.dev.torhugo.application.ports.repository.RouteRepository;
import com.dev.torhugo.domain.exception.InvalidArgumentException;

import java.util.Objects;

import static com.dev.torhugo.application.mappers.PositionMapper.mappingToBasic;

public class UpdatePositionUseCase extends DefaultUseCase {
    private final PositionRepository positionRepository;
    private final RouteRepository routeRepository;

    public UpdatePositionUseCase(final PositionRepository positionRepository,
                                 final RouteRepository routeRepository) {
        this.positionRepository = positionRepository;
        this.routeRepository = routeRepository;
    }

    public UcBasicPositionDTO execute(final UcUpdatePositionDTO input){
        logger.info("Executing use-case: UpdatePosition().");
        final var route = routeRepository.findById(input.routeId());
        if (!Objects.equals(route.getStatus(), "confirmed"))
            throw new InvalidArgumentException("Invalid route status!");
        final var position = positionRepository.findPositionByRoute(route.getRouteId());
        position.calculateDistanceAndVelocity(input.newPosition().latitude(), input.newPosition().longitude());
        position.updatePosition(input.newPosition().latitude(), input.newPosition().longitude());
        routeRepository.save(route);
        positionRepository.save(position);
        return mappingToBasic(position);
    }
}
