package com.dev.torhugo.api.controller;

import com.dev.torhugo.api.RouteAPI;
import com.dev.torhugo.api.models.request.BasicRouteRequest;
import com.dev.torhugo.api.models.response.RouteCreateResponse;
import com.dev.torhugo.models.RouteDTO;
import com.dev.torhugo.usecase.CreateRouteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RouteController implements RouteAPI {
    private final CreateRouteUseCase createRouteUseCase;
    @Override
    public RouteCreateResponse createAccount(final BasicRouteRequest request) {
        final var input = new RouteDTO(
                request.accountId(),
                request.coordinate().latitude(),
                request.coordinate().longitude()
        );
        return new RouteCreateResponse(createRouteUseCase.execute(input));
    }
}
