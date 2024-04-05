package com.dev.torhugo.models;

import java.util.List;
import java.util.UUID;

public record UcInativateAccountDTO(
        List<UUID> accounts
) {
}
