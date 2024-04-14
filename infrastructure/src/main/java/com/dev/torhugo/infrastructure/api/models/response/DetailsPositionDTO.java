package com.dev.torhugo.infrastructure.api.models.response;

import com.dev.torhugo.infrastructure.api.models.CoordinateDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record DetailsPositionDTO(
        @JsonProperty("total_distance") Double totalDistance,
        @JsonProperty("max_velocity") Double maxVelocity,
        @JsonProperty("min_velocity") Double minVelocity,
        @JsonProperty("last_coordinate") CoordinateDTO lastCoordinate,

        @JsonProperty("created_at")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
        LocalDateTime createdAt
) {
}
