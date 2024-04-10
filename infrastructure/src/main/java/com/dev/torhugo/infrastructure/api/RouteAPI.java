package com.dev.torhugo.infrastructure.api;

import com.dev.torhugo.infrastructure.api.models.request.CreateRouteDTO;
import com.dev.torhugo.infrastructure.api.models.response.BasicRouteDTO;
import com.dev.torhugo.infrastructure.api.models.response.RouteCreateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RequestMapping(value = "/route")
public interface RouteAPI {

    @PostMapping("/public/request")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    RouteCreateDTO createAccount(final @RequestBody CreateRouteDTO request);

    @GetMapping("/public/find/{routeId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    BasicRouteDTO findRoute(final @PathVariable(name = "routeId") UUID routeId,
                            final Principal principal);
}
