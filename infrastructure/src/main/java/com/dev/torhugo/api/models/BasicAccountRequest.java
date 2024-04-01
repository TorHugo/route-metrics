package com.dev.torhugo.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BasicAccountRequest(
        @JsonProperty("name") String name,
        @JsonProperty("email") String email,
        @JsonProperty("password") String password
) {
}
