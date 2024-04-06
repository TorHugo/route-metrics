package com.dev.torhugo.infrastructure.api.models.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record InativateAccountDTO(
        @JsonProperty("accounts") @NotBlank @NotNull List<UUID> accouts
) {
}
