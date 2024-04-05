package com.dev.torhugo.dtos;

import java.util.List;
import java.util.UUID;

public record UcInativateAccountDTO(
        List<UUID> accounts
) {
}
