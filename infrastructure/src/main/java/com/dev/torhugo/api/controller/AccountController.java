package com.dev.torhugo.api.controller;

import com.dev.torhugo.usecase.CreateAccountUseCase;
import com.dev.torhugo.usecase.FindAccountUseCase;
import com.dev.torhugo.api.AccountAPI;
import com.dev.torhugo.api.mappers.AccountMapper;
import com.dev.torhugo.api.models.response.AccountCreateResponse;
import com.dev.torhugo.api.models.request.BasicAccountRequest;
import com.dev.torhugo.api.models.response.BasicAccountResponse;
import com.dev.torhugo.models.AccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AccountController implements AccountAPI {
    private final CreateAccountUseCase createAccountUseCase;
    private final FindAccountUseCase findAccountUseCase;
    private final AccountMapper accountMapper;
    @Override
    public AccountCreateResponse createAccount(final BasicAccountRequest request) {
        final var input = new AccountDTO(
                request.name(),
                request.email(),
                request.password()
        );
        return new AccountCreateResponse(createAccountUseCase.execute(input));
    }
    @Override
    public BasicAccountResponse findAccount(final UUID accountId) {
        return accountMapper.mapperToBasicAccount(findAccountUseCase.execute(accountId));
    }
}
