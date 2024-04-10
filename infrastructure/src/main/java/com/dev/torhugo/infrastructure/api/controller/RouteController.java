package com.dev.torhugo.infrastructure.api.controller;

import com.dev.torhugo.application.dto.UcRouteDTO;
import com.dev.torhugo.application.usecase.ConfirmRouteUseCase;
import com.dev.torhugo.application.usecase.FindAllRouteUseCase;
import com.dev.torhugo.application.usecase.FindRouteUseCase;
import com.dev.torhugo.application.usecase.RequestRouteUseCase;
import com.dev.torhugo.infrastructure.api.RouteAPI;
import com.dev.torhugo.infrastructure.api.mappers.RouteMapper;
import com.dev.torhugo.infrastructure.api.models.request.CreateRouteDTO;
import com.dev.torhugo.infrastructure.api.models.response.BasicRouteDTO;
import com.dev.torhugo.infrastructure.api.models.response.RouteCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RouteController implements RouteAPI {
    private final RequestRouteUseCase requestRouteUseCase;
    private final FindRouteUseCase findRouteUseCase;
    private final ConfirmRouteUseCase confirmRouteUseCase;
    private final FindAllRouteUseCase findAllRouteUseCase;
    private final RouteMapper routeMapper;
    @Override
    public RouteCreateDTO createAccount(final CreateRouteDTO request) {
        final var input = new UcRouteDTO(
                request.accountId(),
                request.coordinate().latitude(),
                request.coordinate().longitude()
        );
        return new RouteCreateDTO(requestRouteUseCase.execute(input));
    }

    @Override
    public BasicRouteDTO findRoute(final UUID routeId,
                                   final Principal principal) {
        final var result = findRouteUseCase.execute(routeId, principal.getName());
        return RouteMapper.mapperToBasicRoute(result);
    }

    @Override
    public void confirmRoute(final UUID routeId,
                             final Principal principal) {
        confirmRouteUseCase.execute(routeId, principal.getName());
    }

    @Override
    public List<BasicRouteDTO> findAllRoutes(final Principal principal) {
        final var result = findAllRouteUseCase.execute(principal.getName());
        return routeMapper.mapperToBasicRoutes(result);
    }
}
