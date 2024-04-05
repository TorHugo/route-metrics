package com.dev.torhugo.dtos;

public record UcAccountAdminDTO(
        String name,
        String email,
        String password,
        boolean active,
        boolean admin
) {
}
