package com.dev.torhugo.usecase;

import com.dev.torhugo.dto.UcBasicRouteDTO;
import com.dev.torhugo.ports.repository.RouteRepository;

import java.util.UUID;

import static com.dev.torhugo.mappers.RouteMapper.mappingToBasic;

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
