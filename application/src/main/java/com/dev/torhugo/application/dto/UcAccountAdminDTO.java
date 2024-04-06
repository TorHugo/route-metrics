package com.dev.torhugo.application.dto;

public record UcAccountAdminDTO(
        String name,
        String email,
        String password,
        boolean active,
        boolean admin
) {
}
