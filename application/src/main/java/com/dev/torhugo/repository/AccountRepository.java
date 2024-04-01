package com.dev.torhugo.repository;

import com.dev.torhugo.domain.entity.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    Account findByEmail(final String email);
    void save(final Account actualAccount);
    Account findByAccountId(final UUID accountId);
}
