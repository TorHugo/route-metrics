package com.dev.torhugo.api.models.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AccountAdminDTO(
        @JsonProperty("name") @NotBlank @NotNull String name,
        @JsonProperty("email") @NotBlank @NotNull String email,
        @JsonProperty("password") @NotBlank @NotNull String password,
        @JsonProperty("admin") @NotBlank @NotNull boolean admin,
        @JsonProperty("active") @NotBlank @NotNull boolean active
) {
}
