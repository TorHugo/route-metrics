package com.dev.torhugo.infrastructure.api.models.request;

import com.dev.torhugo.infrastructure.api.models.CoordinateDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record CreateRouteDTO(
        @JsonProperty("account_id") UUID accountId,
        @JsonProperty("initial_coord") CoordinateDTO coordinate
) {
}
