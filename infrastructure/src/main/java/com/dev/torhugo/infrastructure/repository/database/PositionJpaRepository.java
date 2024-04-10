package com.dev.torhugo.infrastructure.repository.database;

import com.dev.torhugo.infrastructure.repository.models.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PositionJpaRepository extends JpaRepository<PositionEntity, UUID> {
    @Query("select p from position_tb p where p.routeId = ?1 order by p.createdAt DESC")
    List<PositionEntity> findByRouteIdOrderByCreatedAtDesc(final UUID routeId);
}
