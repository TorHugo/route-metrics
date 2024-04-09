package com.dev.torhugo.application.dto.messaging;

import java.time.LocalDateTime;

public record ForgetPasswordMailDTO(
        String email,
        String hash,
        LocalDateTime expiration
) {
}
