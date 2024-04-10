package com.dev.torhugo.infrastructure.repository;

import com.dev.torhugo.domain.entity.Route;
import com.dev.torhugo.domain.exception.RepositoryException;
import com.dev.torhugo.application.ports.repository.RouteRepository;
import com.dev.torhugo.infrastructure.repository.database.RouteJpaRepository;
import com.dev.torhugo.infrastructure.repository.models.RouteEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RouteRepositoryImpl implements RouteRepository {
    private final RouteJpaRepository routeJpaRepository;
    @Override
    public void save(final Route actualRoute) {
        final var entity = RouteEntity.fromAggregate(actualRoute);
        routeJpaRepository.save(entity);
    }

    @Override
    public Route findByIdAndAccount(final UUID routeId,
                                    final UUID accountId) {
        final var entity = routeJpaRepository.findByRouteIdAndAccountId(routeId, accountId);
        return entity.map(RouteEntity::toAggregate)
                .orElseThrow(() -> new RepositoryException("Account not found!"));
    }

    @Override
    public List<Route> findAllByAccountAndStatus(final UUID accountId,
                                                 final String status) {
        final var entitys = routeJpaRepository.findByAccountIdAndDifferentStatus(accountId, status);
        return entitys.stream().map(RouteEntity::toAggregate).toList();
    }

    @Override
    public List<Route> findAllByAccount(final UUID accountId) {
        final var entitys = routeJpaRepository.findAllByAccountId(accountId);
        return entitys.stream().map(RouteEntity::toAggregate).toList();
    }
}
