package com.dev.torhugo.infrastructure.api.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record FinishRouteDTO(
        @JsonProperty("route_id") @NotBlank @NotNull UUID routeId,
        @JsonProperty("name") @NotBlank @NotNull String name
) {
}
