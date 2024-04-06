package com.dev.torhugo.application.dto;

import java.util.UUID;

public record UcRouteDTO(
        UUID accountId,
        Double latitude,
        Double longitude
) {
}
