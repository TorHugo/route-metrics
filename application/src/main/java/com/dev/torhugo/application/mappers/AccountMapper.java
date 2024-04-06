package com.dev.torhugo.application.mappers;

import com.dev.torhugo.application.dto.UcBasicAccountDTO;
import com.dev.torhugo.domain.entity.Account;

import java.util.List;

public class AccountMapper {
    private AccountMapper(){}
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
