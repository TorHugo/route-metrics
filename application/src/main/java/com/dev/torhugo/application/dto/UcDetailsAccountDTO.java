package com.dev.torhugo.application.dto;

import java.util.UUID;

public record UcDetailsAccountDTO(
        UUID accountId,
        String name
) {
}
