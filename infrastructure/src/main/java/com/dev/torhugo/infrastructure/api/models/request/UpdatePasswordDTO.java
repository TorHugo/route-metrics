package com.dev.torhugo.infrastructure.api.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatePasswordDTO(
        @JsonProperty("hash_code") @NotNull @NotBlank String hash,
        @JsonProperty("email") @NotNull @NotBlank String email,
        @JsonProperty("password") @NotNull @NotBlank String password
) {
}
