package com.dev.torhugo.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UcBasicRouteDTO(
        UUID routeId,
        UUID accountId,
        Double distance,
        String status,
        boolean active,
        UcCoordinateDTO initialCoord,
        UcCoordinateDTO lastCoord,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
