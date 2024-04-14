package com.dev.torhugo.infrastructure.api.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record DetailsDTO(
        @JsonProperty("account") DetailsAccountDTO detailsAccount,
        @JsonProperty("route") DetailsRouteDTO detailsRoute
) {
}
