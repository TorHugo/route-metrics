package com.dev.torhugo.dto;

import java.util.List;
import java.util.UUID;

public record UcInativateAccountDTO(
        List<UUID> accounts
) {
}
