package com.dev.torhugo.infrastructure.api.controller;

import com.dev.torhugo.application.usecase.*;
import com.dev.torhugo.infrastructure.api.RouteAPI;
import com.dev.torhugo.infrastructure.api.mappers.DetailsMapper;
import com.dev.torhugo.infrastructure.api.mappers.RouteMapper;
import com.dev.torhugo.infrastructure.api.models.request.CreateRouteDTO;
import com.dev.torhugo.infrastructure.api.models.request.FinishRouteDTO;
import com.dev.torhugo.infrastructure.api.models.response.BasicRouteDTO;
import com.dev.torhugo.infrastructure.api.models.response.DetailsDTO;
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
    private final InativateRouteUseCase inativateRouteUseCase;
    private final FindDetailsRouteUseCase findDetailsRouteUseCase;
    private final FinishRouteUseCase finishRouteUseCase;
    private final RouteMapper routeMapper;
    private final DetailsMapper detailsMapper;
    @Override
    public RouteCreateDTO createAccount(final CreateRouteDTO request) {
        return routeMapper.mapperFromUseCase(requestRouteUseCase.execute(routeMapper.mapperToUseCase(request)));
    }

    @Override
    public BasicRouteDTO findRoute(final UUID routeId,
                                   final Principal principal) {
        return routeMapper.mapperFromUseCase(findRouteUseCase.execute(routeId, principal.getName()));
    }

    @Override
    public void confirmRoute(final UUID routeId,
                             final Principal principal) {
        confirmRouteUseCase.execute(routeId, principal.getName());
    }

    @Override
    public List<BasicRouteDTO> findAllRoutes(final Principal principal) {
        return routeMapper.mapperFromUseCase(findAllRouteUseCase.execute(principal.getName()));
    }

    @Override
    public void inativate(final Principal principal,
                          final UUID routeId) {
        inativateRouteUseCase.execute(principal.getName(), routeId);
    }

    @Override
    public DetailsDTO findDetailsRoute(final UUID routeId,
                                       final Principal principal) {
        return detailsMapper.mapperFromUseCase(findDetailsRouteUseCase.execute(principal.getName(), routeId));
    }

    @Override
    public void finish(final FinishRouteDTO request,
                       final Principal principal) {
        finishRouteUseCase.execute(principal.getName(), routeMapper.mapperToUseCase(request));
    }
}
