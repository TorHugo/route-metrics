package com.dev.torhugo.infrastructure.service.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record SendEmailDTO(
        String email,
        String hash,
        LocalDateTime expirationDate
) {
}
