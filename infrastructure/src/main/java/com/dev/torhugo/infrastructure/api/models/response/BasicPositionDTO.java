package com.dev.torhugo.infrastructure.api.models.response;

import com.dev.torhugo.infrastructure.api.models.CoordinateDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record BasicPositionDTO(
        @JsonProperty("position_id")UUID positionId,
        @JsonProperty("coordinate") CoordinateDTO coordinate,
        @JsonProperty("velocity") Double velocity,
        @JsonProperty("distance") Double distance,
        @JsonProperty("created_at")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
        LocalDateTime createdAt
) {
}
