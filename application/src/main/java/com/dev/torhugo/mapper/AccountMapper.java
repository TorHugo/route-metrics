package com.dev.torhugo.mapper;

import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.models.BasicAccountDTO;

public interface AccountMapper {
    BasicAccountDTO mapperToBasic(final Account account);
}
