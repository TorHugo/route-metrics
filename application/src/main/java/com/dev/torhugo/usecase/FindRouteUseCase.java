package com.dev.torhugo.usecase;

import com.dev.torhugo.mapper.RouteMapper;
import com.dev.torhugo.models.UcBasicRouteDTO;
import com.dev.torhugo.repository.RouteRepository;

import java.util.UUID;

public class FindRouteUseCase {
    private final RouteRepository routeRepository;
    private final RouteMapper routeMapper;

    public FindRouteUseCase(final RouteRepository routeRepository,
                            final RouteMapper routeMapper) {
        this.routeRepository = routeRepository;
        this.routeMapper = routeMapper;
    }

    public UcBasicRouteDTO execute(final UUID routeId) {
        final var existingRoute = routeRepository.findByRouteId(routeId);
        return routeMapper.mappingToBasic(existingRoute);
    }
}
