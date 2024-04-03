package com.dev.torhugo.api.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record RouteCreateResponse(
        @JsonProperty("route_id") UUID routeId
) {
}
