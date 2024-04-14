package com.dev.torhugo.application.dto;

import java.time.LocalDateTime;

public record UcDetailsPositionDTO(
        Double totalDistance,
        Double maxVelocity,
        Double minVelocity,
        UcCoordinateDTO lastCoordinate,
        LocalDateTime createdAt
) {
}
