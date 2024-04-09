package com.dev.torhugo.infrastructure.api.mappers;

import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.infrastructure.api.models.response.BasicAccountDTO;
import com.dev.torhugo.application.dto.UcBasicAccountDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountMapper {

    public BasicAccountDTO mapperToBasicAccount(final Account account) {
        return BasicAccountDTO.builder()
                .accountId(account.getAccountId())
                .name(account.getName())
                .email(account.getEmail())
                .active(account.isActive())
                .admin(account.isAdmin())
                .lastAccess(account.getLastAccess())
                .createdAt(account.getCreatedAt())
                .updatedAt(account.getUpdatedAt())
                .build();
    }

    public List<BasicAccountDTO> mapperToBasicListAccount(final List<UcBasicAccountDTO> accounts) {
        return accounts.stream().map(account ->
                BasicAccountDTO.builder()
                    .accountId(account.accountId())
                    .name(account.name())
                    .email(account.email())
                    .active(account.active())
                    .admin(account.admin())
                    .lastAccess(account.lastAccess())
                    .createdAt(account.createdAt())
                    .updatedAt(account.updatedAt())
                    .build()
            ).toList();
    }
}
