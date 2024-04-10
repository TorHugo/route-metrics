package com.dev.torhugo.infrastructure.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CoordinateDTO(
        @JsonProperty("latitude") Double latitude,
        @JsonProperty("longitude") Double longitude
) {
}