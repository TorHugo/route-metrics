package com.dev.torhugo.infrastructure.api.models.response;

import com.dev.torhugo.infrastructure.api.models.CoordinateDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record DetailsRouteDTO(
        @JsonProperty("route_id") UUID routeId,
        @JsonProperty("name") String name,
        @JsonProperty("status") String status,
        @JsonProperty("start_coordinate") CoordinateDTO startCoordinate,
        @JsonProperty("in_active") boolean inActive,
        @JsonProperty("position") DetailsPositionDTO position,

        @JsonProperty("created_at")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
        LocalDateTime createdAt,
        @JsonProperty("updated_at")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        LocalDateTime updatedAt
        ) {
}
