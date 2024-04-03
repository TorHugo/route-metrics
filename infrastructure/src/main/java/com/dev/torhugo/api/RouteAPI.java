package com.dev.torhugo.api;

import com.dev.torhugo.api.models.request.BasicAccountRequest;
import com.dev.torhugo.api.models.request.BasicRouteRequest;
import com.dev.torhugo.api.models.response.AccountCreateResponse;
import com.dev.torhugo.api.models.response.BasicAccountResponse;
import com.dev.torhugo.api.models.response.RouteCreateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping(value = "/route")
public interface RouteAPI {

    @PostMapping(
            "/create"
    )
    @ResponseStatus(HttpStatus.CREATED)
    RouteCreateResponse createAccount(final @RequestBody BasicRouteRequest request);
}
