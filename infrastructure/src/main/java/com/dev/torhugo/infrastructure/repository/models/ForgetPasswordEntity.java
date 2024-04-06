package com.dev.torhugo.infrastructure.repository.models;

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
    private LocalDateTime createdAt;
}
