package com.dev.torhugo.application.usecase;

import com.dev.torhugo.application.dto.UcBasicRouteDTO;
import com.dev.torhugo.application.ports.repository.RouteRepository;

import java.util.UUID;

import static com.dev.torhugo.application.mappers.RouteMapper.mappingToBasic;

public class FindRouteUseCase {
    private final RouteRepository routeRepository;

    public FindRouteUseCase(final RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public UcBasicRouteDTO execute(final UUID routeId) {
        final var existingRoute = routeRepository.findByRouteId(routeId);
        return mappingToBasic(existingRoute);
    }
}
