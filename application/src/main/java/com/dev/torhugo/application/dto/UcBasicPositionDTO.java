package com.dev.torhugo.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UcBasicPositionDTO(
        UUID positionId,
        UcCoordinateDTO lastCoordinate,
        LocalDateTime lastTime,
        Double maxVelocity,
        Double minVelocity,
        Double distance,
        LocalDateTime createdAt
) {
}
