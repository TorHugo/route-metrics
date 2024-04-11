package com.dev.torhugo.infrastructure.repository;

import com.dev.torhugo.application.ports.repository.PositionRepository;
import com.dev.torhugo.domain.entity.Position;
import com.dev.torhugo.domain.exception.RepositoryException;
import com.dev.torhugo.infrastructure.repository.database.PositionJpaRepository;
import com.dev.torhugo.infrastructure.repository.models.PositionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PositionRepositoryImpl implements PositionRepository {
    private final PositionJpaRepository positionJpaRepository;

    @Override
    public Position findPositionByRoute(final UUID routeId) {
        final var entity = positionJpaRepository.findByRouteId(routeId);
        return entity.map(PositionEntity::toAggregate)
                .orElseThrow(() -> new RepositoryException("Position not found!"));
    }

    @Override
    public void save(final Position position) {
        final var entity = PositionEntity.fromAggregate(position);
        positionJpaRepository.save(entity);
    }
}
