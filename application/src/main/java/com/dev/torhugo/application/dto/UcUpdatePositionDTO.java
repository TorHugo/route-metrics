package com.dev.torhugo.application.dto;

import java.util.UUID;

public record UcUpdatePositionDTO(
        UUID routeId,
        UcCoordinateDTO newPosition
) {
}
