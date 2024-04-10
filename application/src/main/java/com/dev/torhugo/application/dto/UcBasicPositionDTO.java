package com.dev.torhugo.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UcBasicPositionDTO(
        UUID positionId,
        UcCoordinateDTO coordinate,
        Double velocity,
        Double distance,
        LocalDateTime createdAt
) {
}
