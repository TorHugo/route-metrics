package com.dev.torhugo.api;

import com.dev.torhugo.api.models.AccountCreateResponse;
import com.dev.torhugo.api.models.BasicAccountRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping(value = "/account")
public interface AccountAPI {

    @PostMapping(
            "/create"
    )
    @ResponseStatus(HttpStatus.CREATED)
    AccountCreateResponse createAccount(final @RequestBody BasicAccountRequest request);
}
