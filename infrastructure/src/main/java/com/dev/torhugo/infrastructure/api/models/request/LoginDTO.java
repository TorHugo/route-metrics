package com.dev.torhugo.infrastructure.api.models.request;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
