package com.dev.torhugo.mapper;

import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.models.BasicAccountDTO;
import org.springframework.stereotype.Component;

@Component
public class AccountMapperImpl implements AccountMapper {
    @Override
    public BasicAccountDTO mapperToBasic(final Account account) {
        return new BasicAccountDTO(
                account.getAccountId(),
                account.getName(),
                account.getEmail(),
                account.isActive(),
                account.isAdmin(),
                account.getLastAccess(),
                account.getCreatedAt(),
                account.getUpdatedAt()
        );
    }
}
