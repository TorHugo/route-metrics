package com.dev.torhugo.application.dto;

import java.time.LocalDateTime;

public record UcCoordinateDTO(
        Double latitude,
        Double longitude,
        LocalDateTime time
) {
}
