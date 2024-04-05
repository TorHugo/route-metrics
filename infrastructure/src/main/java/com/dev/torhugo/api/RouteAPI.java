package com.dev.torhugo.api;

import com.dev.torhugo.api.models.request.BasicRouteDTO;
import com.dev.torhugo.api.models.response.RouteCreateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/route")
public interface RouteAPI {

    @PostMapping(
            "/create"
    )
    @ResponseStatus(HttpStatus.CREATED)
    RouteCreateDTO createAccount(final @RequestBody BasicRouteDTO request);
}
