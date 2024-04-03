package com.dev.torhugo.models;

import java.util.UUID;

public record RouteDTO(
        UUID accountId,
        Double latitude,
        Double longitude
) {
}
