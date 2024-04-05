package com.dev.torhugo.dtos;

import java.util.UUID;

public record UcRouteDTO(
        UUID accountId,
        Double latitude,
        Double longitude
) {
}
