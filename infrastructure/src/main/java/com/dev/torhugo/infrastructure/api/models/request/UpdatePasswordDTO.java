package com.dev.torhugo.infrastructure.api.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatePasswordDTO(
        @JsonProperty("old_password") @NotBlank @NotNull String oldPassword,
        @JsonProperty("new_password") @NotBlank @NotNull String newPassword
) {
}
