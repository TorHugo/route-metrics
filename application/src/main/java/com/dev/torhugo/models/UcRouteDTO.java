package com.dev.torhugo.models;

import java.util.UUID;

public record UcRouteDTO(
        UUID accountId,
        Double latitude,
        Double longitude
) {
}
