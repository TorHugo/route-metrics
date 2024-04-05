package com.dev.torhugo.dtos;

import java.util.UUID;

public record UcUpdateAccountDTO(
        UUID accountId,
        String name,
        String email,
        String password,
        boolean active,
        boolean admin
) {
}
