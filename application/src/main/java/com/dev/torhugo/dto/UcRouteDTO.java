package com.dev.torhugo.dto;

import java.util.UUID;

public record UcRouteDTO(
        UUID accountId,
        Double latitude,
        Double longitude
) {
}
