package com.dev.torhugo.repository;

import com.dev.torhugo.domain.entity.Route;
import com.dev.torhugo.repository.models.RouteEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

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
}
