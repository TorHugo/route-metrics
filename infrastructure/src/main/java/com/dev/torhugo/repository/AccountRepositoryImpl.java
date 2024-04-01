package com.dev.torhugo.repository;

import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.repository.models.AccountEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AccountRepositoryImpl implements AccountRepository {
    private final AccountJpaRepository repository;
    @Override
    public Account findByEmail(final String email) {
        final var accountEntity = repository.findByEmail(email);
        return accountEntity.map(AccountEntity::toAggregate)
                .orElse(null);
    }
    @Override
    public void save(final Account actualAccount) {
        final var accountEntity = AccountEntity.fromAggregate(actualAccount);
        repository.save(accountEntity);
    }

    @Override
    public Account findByAccountId(final UUID accountId) {
        final var accountEntity = repository.findById(accountId);
        return accountEntity.map(AccountEntity::toAggregate)
                .orElse(null);
    }
}
