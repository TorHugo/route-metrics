package com.dev.torhugo.infrastructure.repository.models;

import com.dev.torhugo.domain.entity.ForgetPassword;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Table
@Entity(name = "forget_password_tb")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ForgetPasswordEntity {
    @Id
    private UUID forgetPasswordId;
    private UUID accountId;
    private String hashCode;
    private LocalDateTime expirationAt;
    private boolean active;
    private boolean confirmed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ForgetPasswordEntity fromAggregate(final ForgetPassword domain){
        return new ForgetPasswordEntity(
                domain.getForgetPasswordId(),
                domain.getAccountId(),
                domain.getHashCode(),
                domain.getExpirationDate(),
                domain.isActive(),
                domain.isConfirmed(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        );
    }

    public static ForgetPassword toAggregate(final ForgetPasswordEntity entity){
        return ForgetPassword.restore(
                entity.forgetPasswordId,
                entity.accountId,
                entity.hashCode,
                entity.expirationAt,
                entity.active,
                entity.confirmed,
                entity.createdAt,
                entity.updatedAt
        );
    }
}
