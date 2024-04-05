package com.dev.torhugo.api.models.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record UpdateAccountDTO(
        @JsonProperty("name") String name,
        @JsonProperty("email") String email,
        @JsonProperty("password") String password,
        @JsonProperty("active") @NotNull boolean active,
        @JsonProperty("admin") @NotNull boolean admin
) {
}
