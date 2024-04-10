package com.dev.torhugo.infrastructure.api.models.request;

import com.dev.torhugo.infrastructure.api.models.CoordinateDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record CreatePositionDTO(
        @JsonProperty("route_id") UUID routeId,
        @JsonProperty("coordinate") CoordinateDTO coordinate
) {
}
