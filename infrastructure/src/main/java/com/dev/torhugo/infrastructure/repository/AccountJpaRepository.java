package com.dev.torhugo.infrastructure.repository;

import com.dev.torhugo.infrastructure.repository.models.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, UUID> {
    Optional<AccountEntity> findByEmail(final String email);
    List<AccountEntity> findAllByAccountIdInAndActiveTrue(final List<UUID> accountIds);
}
