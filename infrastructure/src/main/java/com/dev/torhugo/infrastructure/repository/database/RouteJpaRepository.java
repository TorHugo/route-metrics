package com.dev.torhugo.infrastructure.repository.database;

import com.dev.torhugo.infrastructure.repository.models.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RouteJpaRepository extends JpaRepository<RouteEntity, UUID> {
    Optional<RouteEntity> findByRouteIdAndAccountId(final UUID routeId,
                                                    final UUID accountId);
    List<RouteEntity> findAllByAccountId(final UUID accountId);
    @Query("SELECT r FROM route_tb r WHERE r.accountId = ?1 AND r.status <> ?2")
    List<RouteEntity> findByAccountIdAndDifferentStatus(final UUID accountId,
                                                        final String status);

}
