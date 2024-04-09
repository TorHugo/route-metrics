package com.dev.torhugo.domain.entity;

import com.dev.torhugo.domain.service.CreateHashCode;
import com.dev.torhugo.domain.vo.HashCode;

import java.time.LocalDateTime;
import java.util.UUID;

public class ForgetPassword {
    private final UUID forgetPasswordId;
    private final UUID accountId;
    private final HashCode hashCode;
    private final LocalDateTime expirationDate;
    private final boolean active;
    private final boolean confirmed;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private ForgetPassword(final UUID forgetPasswordId,
                           final UUID accountId,
                           final String hashCode,
                           final LocalDateTime expirationDate,
                           final boolean active,
                           final boolean confirmed,
                           final LocalDateTime createdAt,
                           final LocalDateTime updatedAt) {
        this.forgetPasswordId = forgetPasswordId;
        this.accountId = accountId;
        this.hashCode = new HashCode(hashCode);
        this.expirationDate = expirationDate;
        this.active = active;
        this.confirmed = confirmed;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ForgetPassword create(final UUID accountId){
        final var uuid = UUID.randomUUID();
        final var dateNow = LocalDateTime.now();
        final var isActive = true;
        final var isConfirmed = false;
        final var expirationRuleTime = 30L;
        final var expirationDate = dateNow.plusMinutes(expirationRuleTime);
        final var hashCode = CreateHashCode.create();
        return new ForgetPassword(
                uuid,
                accountId,
                hashCode,
                expirationDate,
                isActive,
                isConfirmed,
                dateNow,
                null
        );
    }



    public static ForgetPassword confirmed(final UUID forgetPasswordId,
                                           final UUID accountId,
                                           final String hashCode,
                                           final boolean active,
                                           final LocalDateTime expirationDate,
                                           final LocalDateTime createdAt){
        final var dateNow = LocalDateTime.now();
        final var confirmed = true;
        return new ForgetPassword(
                forgetPasswordId,
                accountId,
                hashCode,
                expirationDate,
                active,
                confirmed,
                createdAt,
                dateNow
        );
    }

    public static ForgetPassword inactived(final UUID forgetPasswordId,
                                           final UUID accountId,
                                           final String hashCode,
                                           final boolean confirmed,
                                           final LocalDateTime expirationDate,
                                           final LocalDateTime createdAt){
        final var dateNow = LocalDateTime.now();
        final var active = false;
        return new ForgetPassword(
                forgetPasswordId,
                accountId,
                hashCode,
                expirationDate,
                active,
                confirmed,
                createdAt,
                dateNow
        );
    }
    public static ForgetPassword restore(final UUID forgetPasswordId,
                                         final UUID accountId,
                                         final String hashCode,
                                         final LocalDateTime expirationDate,
                                         final boolean active,
                                         final boolean confirmed,
                                         final LocalDateTime createdAt,
                                         final LocalDateTime updatedAt){
        return new ForgetPassword(
                forgetPasswordId,
                accountId,
                hashCode,
                expirationDate,
                active,
                confirmed,
                createdAt,
                updatedAt
        );
    }

    public UUID getForgetPasswordId() {
        return forgetPasswordId;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public String getHashCode() {
        return hashCode.getValue();
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
