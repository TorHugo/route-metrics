package com.dev.torhugo.api.mappers;

import com.dev.torhugo.api.models.response.BasicAccountResponse;
import com.dev.torhugo.models.BasicAccountDTO;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public BasicAccountResponse mapperToBasicAccount(final BasicAccountDTO account) {
        return BasicAccountResponse.builder()
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
}
