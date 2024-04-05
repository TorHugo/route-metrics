package com.dev.torhugo.api.controller;

import com.dev.torhugo.api.RouteAPI;
import com.dev.torhugo.api.models.request.BasicRouteDTO;
import com.dev.torhugo.api.models.response.RouteCreateDTO;
import com.dev.torhugo.models.UcRouteDTO;
import com.dev.torhugo.usecase.CreateRouteUseCase;
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
