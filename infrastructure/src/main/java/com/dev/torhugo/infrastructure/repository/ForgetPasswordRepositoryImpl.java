package com.dev.torhugo.infrastructure.repository;

import com.dev.torhugo.application.ports.repository.ForgetPasswordRepository;
import com.dev.torhugo.domain.entity.ForgetPassword;
import com.dev.torhugo.domain.exception.RepositoryException;
import com.dev.torhugo.infrastructure.repository.database.ForgetPasswordJpaRepository;
import com.dev.torhugo.infrastructure.repository.models.ForgetPasswordEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ForgetPasswordRepositoryImpl implements ForgetPasswordRepository {
    private final ForgetPasswordJpaRepository repository;

    @Override
    public void save(final ForgetPassword forgetPassword) {
        final var entity = ForgetPasswordEntity.fromAggregate(forgetPassword);
        repository.save(entity);
    }

    @Override
    public ForgetPassword findHashConfirmedTrue(final String hascode,
                                                final UUID accountId) {
        final var entity = repository.findByHashCodeAndActiveTrueAndAccountId(hascode, accountId);
        return entity.map(ForgetPasswordEntity::toAggregate)
                .orElseThrow(() -> new RepositoryException("HashCode not found!"));
    }

    @Override
    public ForgetPassword findHashConfirmedFalse(final String hashcode,
                                                 final UUID accountId) {
        final var entity = repository.findByHashCodeAndConfirmedFalseAndActiveTrueAndAccountId(hashcode, accountId);
        return entity.map(ForgetPasswordEntity::toAggregate)
                .orElseThrow(() -> new RepositoryException("HashCode not found!"));
    }
}
