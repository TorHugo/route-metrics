package com.dev.torhugo.models;

import java.time.LocalDateTime;
import java.util.UUID;

public record BasicAccountDTO(
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
