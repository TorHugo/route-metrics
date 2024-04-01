package com.dev.torhugo.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record AccountCreateResponse(
        @JsonProperty("account_id") UUID accountId
) {

}
