package com.dev.torhugo.models;

import java.time.LocalDateTime;
import java.util.UUID;

public record BasicRouteDTO(
        UUID routeId,
        UUID accountId,
        Double distance,
        String status,
        boolean active,
        CoordinateDTO initialCoord,
        CoordinateDTO lastCoord,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
