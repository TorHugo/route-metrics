package com.dev.torhugo.api.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ConfirmHashDTO(
        @JsonProperty("hash_code") @NotNull @NotBlank String hash
) {
}
