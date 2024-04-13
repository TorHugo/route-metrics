package com.dev.torhugo.infrastructure.repository.database;

import com.dev.torhugo.infrastructure.repository.models.ForgetPasswordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ForgetPasswordJpaRepository extends JpaRepository<ForgetPasswordEntity, UUID> {

    Optional<ForgetPasswordEntity> findByHashCodeAndConfirmedFalseAndActiveTrueAndAccountId(final String hashCode,
                                                                                            final UUID accountId);
    Optional<ForgetPasswordEntity> findByHashCodeAndActiveTrueAndAccountId(final String hashCode,
                                                                           final UUID accountId);
    Optional<ForgetPasswordEntity> findByAccountId(final UUID accountId);
}
