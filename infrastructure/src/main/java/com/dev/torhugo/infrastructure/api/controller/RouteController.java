package com.dev.torhugo.infrastructure.api.controller;

import com.dev.torhugo.infrastructure.api.RouteAPI;
import com.dev.torhugo.infrastructure.api.models.request.BasicRouteDTO;
import com.dev.torhugo.infrastructure.api.models.response.RouteCreateDTO;
import com.dev.torhugo.application.dto.UcRouteDTO;
import com.dev.torhugo.application.usecase.CreateRouteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RouteController implements RouteAPI {
    private final CreateRouteUseCase createRouteUseCase;
    @Override
    public RouteCreateDTO createAccount(final BasicRouteDTO request) {
        final var input = new UcRouteDTO(
                request.accountId(),
                request.coordinate().latitude(),
                request.coordinate().longitude()
        );
        return new RouteCreateDTO(createRouteUseCase.execute(input));
    }
}