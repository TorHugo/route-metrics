package com.dev.torhugo.api.mappers;

import com.dev.torhugo.api.models.response.BasicAccountDTO;
import com.dev.torhugo.dtos.UcBasicAccountDTO;
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
