package com.dev.torhugo.mapper;

import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.models.UcBasicAccountDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountMapperImpl implements AccountMapper {
    @Override
    public UcBasicAccountDTO mapperToBasic(final Account account) {
        return new UcBasicAccountDTO(
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

    @Override
    public List<UcBasicAccountDTO> mapperToListBasic(final List<Account> accounts) {
        return accounts.stream().map(account ->
                new UcBasicAccountDTO(
                    account.getAccountId(),
                    account.getName(),
                    account.getEmail(),
                    account.isActive(),
                    account.isAdmin(),
                    account.getLastAccess(),
                    account.getCreatedAt(),
                    account.getUpdatedAt()
                )
            ).toList();
    }
}
