package com.dev.torhugo.infrastructure.repository.database;

import com.dev.torhugo.infrastructure.repository.models.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PositionJpaRepository extends JpaRepository<PositionEntity, UUID> {
    Optional<PositionEntity> findByRouteId(final UUID routeId);

}
