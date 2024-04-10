package com.dev.torhugo.application.usecase;

import com.dev.torhugo.application.config.DefaultUseCase;
import com.dev.torhugo.application.dto.UcRouteDTO;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.ports.repository.RouteRepository;
import com.dev.torhugo.domain.entity.Route;

import java.util.UUID;

public class CreateRouteUseCase extends DefaultUseCase {
    private final AccountRepository accountRepository;
    private final RouteRepository routeRepository;
    public CreateRouteUseCase(final AccountRepository accountRepository,
                              final RouteRepository routeRepository) {
        this.accountRepository = accountRepository;
        this.routeRepository = routeRepository;
    }

    public UUID execute(final UcRouteDTO input){
        logger.info("Executing use-case: CreateRoute.");
        final var account = this.accountRepository.findByAccountId(input.accountId());
        final var actualRoute = Route.create(
                account.getAccountId(),
                input.latitude(),
                input.longitude()
        );
        this.routeRepository.save(actualRoute);
        return actualRoute.getRouteId();
    }
}