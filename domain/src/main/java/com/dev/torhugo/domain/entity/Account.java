package com.dev.torhugo.domain.entity;

import com.dev.torhugo.domain.error.exception.InvalidArgumentError;
import com.dev.torhugo.domain.vo.Email;
import com.dev.torhugo.domain.vo.Password;

import java.time.LocalDateTime;
import java.util.UUID;

public class Account {
    private final UUID accountId;
    private final String name;
    private final Email email;
    private final Password password;
    private final boolean active;
    private final boolean admin;
    private final LocalDateTime lastAccess;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private Account(
            final UUID accountId,
            final String name,
            final String email,
            final String password,
            final boolean active,
            final boolean admin,
            final LocalDateTime lastAccess,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
    ){
        this.accountId = accountId;
        this.name = name;
        this.email = new Email(email);
        this.password = new Password(password);
        this.active = active;
        this.admin = admin;
        this.lastAccess = lastAccess;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Account create(final String name,
                                 final String email,
                                 final String password){
        final var accountId = UUID.randomUUID();
        final var isActive = true;
        final var isAdmin = false;
        final var dateNow = LocalDateTime.now();
        return new Account(
                accountId,
                name,
                email,
                password,
                isActive,
                isAdmin,
                dateNow,
                dateNow,
                null
        );
    }

    public Account inactive(final UUID accountId,
                            final String name,
                            final String email,
                            final String password,
                            final boolean isAdmin,
                            final LocalDateTime createdAt){
        if(!this.active) throw new InvalidArgumentError("This account is already inactive.");
        final var isActive = false;
        final var dateNow = LocalDateTime.now();
        return new Account(
                accountId,
                name,
                email,
                password,
                isActive,
                isAdmin,
                createdAt,
                dateNow,
                dateNow
        );
    }

    public static Account restore(final UUID accountId,
                                  final String name,
                                  final String email,
                                  final String password,
                                  final boolean active,
                                  final boolean admin,
                                  final LocalDateTime lastAccess,
                                  final LocalDateTime createdAt,
                                  final LocalDateTime updatedAt){

        return new Account(
                accountId,
                name,
                email,
                password,
                active,
                admin,
                lastAccess,
                createdAt,
                updatedAt
        );
    }

    public UUID getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email.getValue();
    }

    public String getPassword() {
        return password.getValue();
    }

    public boolean isActive() {
        return active;
    }

    public boolean isAdmin() {
        return admin;
    }

    public LocalDateTime getLastAccess() {
        return lastAccess;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
