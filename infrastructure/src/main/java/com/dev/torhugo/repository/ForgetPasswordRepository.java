package com.dev.torhugo.repository;

import com.dev.torhugo.repository.models.ForgetPasswordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ForgetPasswordRepository extends JpaRepository<ForgetPasswordEntity, UUID> {
    Optional<ForgetPasswordEntity> findByHashCodeIgnoreCaseAndActiveTrue(final String hashCode);

}
