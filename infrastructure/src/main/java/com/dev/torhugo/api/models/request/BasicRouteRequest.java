package com.dev.torhugo.api.models.request;

import com.dev.torhugo.api.models.CoordinateDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record BasicRouteRequest(
        @JsonProperty("account_id") UUID accountId,
        @JsonProperty("initial_coord") CoordinateDTO coordinate
) {
}
