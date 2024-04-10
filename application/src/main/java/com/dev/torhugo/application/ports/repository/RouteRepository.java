package com.dev.torhugo.application.ports.repository;

import com.dev.torhugo.domain.entity.Route;

import java.util.List;
import java.util.UUID;

public interface RouteRepository {
    void save(final Route actualRoute);
    Route findByIdAndAccount(final UUID routeId,
                             final UUID accountId);
    List<Route> findAllByAccountAndStatus(final UUID accountId,
                                          final String status);
    List<Route> findAllByAccount(final UUID accountId);
}
