package com.dev.torhugo.application.ports.repository;

import com.dev.torhugo.domain.entity.Position;

import java.util.UUID;

public interface PositionRepository {
    Position findPositionByRoute(final UUID routeId);
    void save(final Position position);
}
