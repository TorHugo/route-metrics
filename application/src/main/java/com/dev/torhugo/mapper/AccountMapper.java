package com.dev.torhugo.mapper;

import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.models.UcBasicAccountDTO;

import java.util.List;

public interface AccountMapper {
    UcBasicAccountDTO mapperToBasic(final Account account);
    List<UcBasicAccountDTO> mapperToListBasic(final List<Account> accounts);
}
