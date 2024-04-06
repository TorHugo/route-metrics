package com.dev.torhugo.application.ports.repository;

import com.dev.torhugo.domain.entity.Route;

import java.util.UUID;

public interface RouteRepository {
    void save(final Route actualRoute);
    Route findByRouteId(final UUID routeId);
}
