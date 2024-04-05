package com.dev.torhugo.repository;

import com.dev.torhugo.domain.entity.Account;

import java.util.List;
import java.util.UUID;

public interface AccountRepository {
    Account findByEmail(final String email);
    Account findByEmailWithThrow(final String email);
    void save(final Account actualAccount);
    Account findByAccountId(final UUID accountId);
    List<Account> findAll();
    List<Account> findAllByIds(final List<UUID> accounts);
    void saveAll(final List<Account> accounts);
}
