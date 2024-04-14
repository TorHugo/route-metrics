package com.dev.torhugo.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UcDetailsRouteDTO(
        UUID routeId,
        String name,
        String status,
        UcCoordinateDTO startCoordinate,
        boolean active,
        UcDetailsPositionDTO position,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
