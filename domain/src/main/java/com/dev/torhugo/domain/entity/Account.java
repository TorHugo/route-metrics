package com.dev.torhugo.domain.entity;

import com.dev.torhugo.domain.exception.InvalidArgumentException;
import com.dev.torhugo.domain.vo.Email;
import com.dev.torhugo.domain.vo.Password;

import java.time.LocalDateTime;
import java.util.UUID;

public class Account {
    private final UUID accountId;
    private final String name;
    private final Email email;
    private Password password;
    private boolean active;
    private final boolean admin;
    private LocalDateTime lastAccess;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

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

    public static Account create(final String name,
                                 final String email,
                                 final String password,
                                 final boolean active,
                                 final boolean admin){
        final var accountId = UUID.randomUUID();
        final var dateNow = LocalDateTime.now();
        return new Account(
                accountId,
                name,
                email,
                password,
                active,
                admin,
                dateNow,
                dateNow,
                null
        );
    }

    public void inactive(){
        if(!this.active) throw new InvalidArgumentException("This account is already inactive.");
        this.active = false;
        this.updatedAt = LocalDateTime.now();
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

    public static Account update(final UUID accountId,
                                 final String name,
                                 final String email,
                                 final String password,
                                 final boolean active,
                                 final boolean admin,
                                 final LocalDateTime lastAccess,
                                 final LocalDateTime createdAt){
        final var dateNow = LocalDateTime.now();
        return new Account(
                accountId,
                name,
                email,
                password,
                active,
                admin,
                lastAccess,
                createdAt,
                dateNow
        );
    }

    public void lastAccess(){
        this.updatedAt = LocalDateTime.now();
        this.lastAccess = LocalDateTime.now();
    }

    public void updatePassword(final String newPassword){
        this.updatedAt = LocalDateTime.now();
        this.password = new Password(newPassword);
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
