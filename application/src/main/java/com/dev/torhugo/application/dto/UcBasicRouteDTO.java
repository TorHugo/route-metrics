package com.dev.torhugo.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UcBasicRouteDTO(
        UUID routeId,
        UUID accountId,
        String status,
        String name,
        boolean active,
        UcCoordinateDTO startCoordinate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
