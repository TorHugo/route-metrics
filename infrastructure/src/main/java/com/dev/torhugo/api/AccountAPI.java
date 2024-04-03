package com.dev.torhugo.api;

import com.dev.torhugo.api.models.response.AccountCreateResponse;
import com.dev.torhugo.api.models.request.BasicAccountRequest;
import com.dev.torhugo.api.models.response.BasicAccountResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping(value = "/account")
public interface AccountAPI {

    @PostMapping(
            "/create"
    )
    @ResponseStatus(HttpStatus.CREATED)
    AccountCreateResponse createAccount(final @RequestBody BasicAccountRequest request);
    @GetMapping(
            "/find/{accountId}"
    )
    @ResponseStatus(HttpStatus.OK)
    BasicAccountResponse findAccount(final @PathVariable UUID accountId);
}
