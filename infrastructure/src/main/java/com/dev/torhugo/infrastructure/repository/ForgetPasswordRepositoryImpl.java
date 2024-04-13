package com.dev.torhugo.infrastructure.repository;

import com.dev.torhugo.application.ports.repository.ForgetPasswordRepository;
import com.dev.torhugo.domain.entity.ForgetPassword;
import com.dev.torhugo.domain.exception.RepositoryException;
import com.dev.torhugo.infrastructure.repository.database.ForgetPasswordJpaRepository;
import com.dev.torhugo.infrastructure.repository.models.ForgetPasswordEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ForgetPasswordRepositoryImpl implements ForgetPasswordRepository {
    private static final String ERROR_HASH_NOT_FOUND = "HashCode not found!";

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
                .orElseThrow(() -> new RepositoryException(ERROR_HASH_NOT_FOUND));
    }

    @Override
    public ForgetPassword findHashConfirmedFalse(final String hashcode,
                                                 final UUID accountId) {
        final var entity = repository.findByHashCodeAndConfirmedFalseAndActiveTrueAndAccountId(hashcode, accountId);
        return entity.map(ForgetPasswordEntity::toAggregate)
                .orElseThrow(() -> new RepositoryException(ERROR_HASH_NOT_FOUND));
    }

    @Override
    public ForgetPassword findHashByAccount(final UUID accountId){
        final var entity = repository.findByAccountId(accountId);
        return entity.map(ForgetPasswordEntity::toAggregate)
                .orElseThrow(() -> new RepositoryException(ERROR_HASH_NOT_FOUND));
    }

    @Override
    public List<ForgetPassword> findAll() {
        final var entities = repository.findAll();
        return entities.stream().map(ForgetPasswordEntity::toAggregate).toList();
    }
}
