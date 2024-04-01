package com.dev.torhugo.api.controller;

import com.dev.torhugo.CreateAccountUseCase;
import com.dev.torhugo.api.AccountAPI;
import com.dev.torhugo.api.models.AccountCreateResponse;
import com.dev.torhugo.api.models.BasicAccountRequest;
import com.dev.torhugo.models.AccountInput;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController implements AccountAPI {
    private final CreateAccountUseCase createAccountUseCase;
    @Override
    public AccountCreateResponse createAccount(final BasicAccountRequest request) {
        final var input = new AccountInput(request.name(), request.email(), request.password());
        final var result = createAccountUseCase.execute(input);
        return new AccountCreateResponse(result);
    }
}
