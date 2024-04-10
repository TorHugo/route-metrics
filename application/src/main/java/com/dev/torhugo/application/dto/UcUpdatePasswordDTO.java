package com.dev.torhugo.application.dto;

public record UcUpdatePasswordDTO(
        String newPassword,
        boolean comparingPassword
) {
}
