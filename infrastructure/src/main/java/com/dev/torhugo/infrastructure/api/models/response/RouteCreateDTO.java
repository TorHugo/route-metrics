package com.dev.torhugo.infrastructure.api.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record RouteCreateDTO(
        @JsonProperty("route_id") UUID routeId
) {
}
