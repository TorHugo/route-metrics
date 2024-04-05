package com.dev.torhugo.mappers;

import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.dtos.UcBasicAccountDTO;

import java.util.List;

public class AccountMapper {
    public static UcBasicAccountDTO mapperToBasic(final Account account) {
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

    public static List<UcBasicAccountDTO> mapperToListBasic(final List<Account> accounts) {
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
