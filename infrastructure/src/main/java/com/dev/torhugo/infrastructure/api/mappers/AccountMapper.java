package com.dev.torhugo.infrastructure.api.mappers;

import com.dev.torhugo.infrastructure.api.models.response.BasicAccountDTO;
import com.dev.torhugo.application.dto.UcBasicAccountDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountMapper {

    public BasicAccountDTO mapperToBasicAccount(final UcBasicAccountDTO account) {
        return BasicAccountDTO.builder()
                .accountId(account.accountId())
                .name(account.name())
                .email(account.email())
                .active(account.active())
                .admin(account.admin())
                .lastAccess(account.lastAccess())
                .createdAt(account.createdAt())
                .updatedAt(account.updatedAt())
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
