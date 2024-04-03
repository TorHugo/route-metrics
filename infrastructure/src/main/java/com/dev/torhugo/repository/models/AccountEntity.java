package com.dev.torhugo.repository.models;

import com.dev.torhugo.domain.entity.Account;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Table
@Entity(name = "account_tb")
@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class AccountEntity {
    @Id
    private UUID accountId;
    private String name;
    private String email;
    private String password;
    private boolean active;
    private boolean admin;
    private LocalDateTime lastAccess;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Account toAggregate(final AccountEntity entity) {
        return Account.restore(
            entity.accountId,
            entity.name,
            entity.email,
            entity.password,
            entity.active,
            entity.admin,
            entity.lastAccess,
            entity.createdAt,
            entity.updatedAt
        );
    }

    public static AccountEntity fromAggregate(final Account account) {
        return new AccountEntity(
                account.getAccountId(),
                account.getName(),
                account.getEmail(),
                account.getPassword(),
                account.isActive(),
                account.isAdmin(),
                account.getLastAccess(),
                account.getCreatedAt(),
                account.getUpdatedAt()
        );
    }
}
