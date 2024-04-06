package com.dev.torhugo.infrastructure.api;

import com.dev.torhugo.infrastructure.api.models.request.BasicRouteDTO;
import com.dev.torhugo.infrastructure.api.models.response.RouteCreateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/route")
public interface RouteAPI {

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    RouteCreateDTO createAccount(final @RequestBody BasicRouteDTO request);
}
