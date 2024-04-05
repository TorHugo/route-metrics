package com.dev.torhugo.repository;

import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.domain.exception.RepositoryException;
import com.dev.torhugo.ports.repository.AccountRepository;
import com.dev.torhugo.repository.models.AccountEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
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
    public Account findByEmailWithThrow(final String email) {
        final var accountEntity = repository.findByEmail(email);
        return accountEntity.map(AccountEntity::toAggregate)
                .orElseThrow(() -> new RepositoryException("Account not found!"));
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
                .orElseThrow(() -> new RepositoryException("Account not found!"));
    }

    @Override
    public List<Account> findAll() {
        final var accounts = repository.findAll();
        return accounts.stream().map(AccountEntity::toAggregate).toList();
    }

    @Override
    public List<Account> findAllByIds(final List<UUID> ids) {
        final var accounts = repository.findAllByAccountIdInAndActiveTrue(ids);
        return accounts.stream().map(AccountEntity::toAggregate).toList();
    }

    @Override
    public void saveAll(final List<Account> accounts) {
        final var accountsEntity = accounts.stream().map(AccountEntity::fromAggregate).toList();
        repository.saveAll(accountsEntity);
    }
}
