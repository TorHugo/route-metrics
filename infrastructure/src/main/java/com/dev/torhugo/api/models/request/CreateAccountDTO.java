package com.dev.torhugo.api.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAccountDTO(
        @JsonProperty("name") @NotBlank @NotNull String name,
        @JsonProperty("email") @NotBlank @NotNull String email,
        @JsonProperty("password") @NotBlank @NotNull String password
) {
}
