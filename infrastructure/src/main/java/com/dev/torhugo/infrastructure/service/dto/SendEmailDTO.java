package com.dev.torhugo.infrastructure.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record SendEmailDTO(
        @JsonProperty("email") String emailTo,
        @JsonProperty("hash_code") String hash,
        @JsonProperty("expiration_date") LocalDateTime expirationDate
) {
}
