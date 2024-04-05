package com.dev.torhugo.models;

public record UcAccountAdminDTO(
        String name,
        String email,
        String password,
        boolean active,
        boolean admin
) {
}
