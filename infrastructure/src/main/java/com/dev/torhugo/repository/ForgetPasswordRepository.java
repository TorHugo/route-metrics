package com.dev.torhugo.repository;

import com.dev.torhugo.repository.models.ForgetPasswordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface ForgetPasswordRepository extends JpaRepository<ForgetPasswordEntity, UUID> {
    ForgetPasswordEntity findByHashCodeIgnoreCaseAndActiveTrue(final String hashCode);
    ForgetPasswordEntity findByAccountIdAndActiveTrue(final UUID accountId);
    @Transactional
    @Modifying
    @Query("update forget_password_tb f set f.active = false where f.forgetPasswordId = ?1")
    void updateActiveHash(final UUID forgetPasswordId);

}
