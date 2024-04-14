package com.dev.torhugo.infrastructure.api.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.UUID;

@Builder
public record DetailsAccountDTO(
        @JsonProperty("account_id") UUID accountId,
        @JsonProperty("name") String name
        ) {
}
