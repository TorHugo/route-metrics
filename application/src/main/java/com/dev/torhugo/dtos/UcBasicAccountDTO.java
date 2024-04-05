package com.dev.torhugo.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record UcBasicAccountDTO(
        UUID accountId,
        String name,
        String email,
        boolean active,
        boolean admin,
        LocalDateTime lastAccess,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}