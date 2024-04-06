package com.dev.torhugo.application.dto;

import java.util.List;
import java.util.UUID;

public record UcInativateAccountDTO(
        List<UUID> accounts
) {
}
