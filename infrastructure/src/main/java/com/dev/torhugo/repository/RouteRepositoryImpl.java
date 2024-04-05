package com.dev.torhugo.repository;

import com.dev.torhugo.domain.entity.Route;
import com.dev.torhugo.domain.error.exception.RepositoryNotFoundError;
import com.dev.torhugo.ports.repository.RouteRepository;
import com.dev.torhugo.repository.models.RouteEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RouteRepositoryImpl implements RouteRepository {
    private final RouteJpaRepository routeJpaRepository;
    @Override
    public void save(final Route actualRoute) {
        final var routeEntity = RouteEntity.fromAggregate(actualRoute);
        routeJpaRepository.save(routeEntity);
    }

    @Override
    public Route findByRouteId(final UUID routeId) {
        final var routeEntity = routeJpaRepository.findById(routeId);
        return routeEntity.map(RouteEntity::toAggregate)
                .orElseThrow(() -> new RepositoryNotFoundError("Account not found!"));
    }
}
