package com.dev.torhugo.application.usecase;

import com.dev.torhugo.application.config.DefaultUseCase;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.ports.repository.RouteRepository;

import java.util.UUID;

public class InativateRouteUseCase extends DefaultUseCase {
    private final RouteRepository routeRepository;
    private final AccountRepository accountRepository;

    public InativateRouteUseCase(final RouteRepository routeRepository,
                                 final AccountRepository accountRepository) {
        this.routeRepository = routeRepository;
        this.accountRepository = accountRepository;
    }

    public void execute(final String email,
                        final UUID routeId){
        final var account = accountRepository.findByEmailWithThrow(email);
        final var route = routeRepository.findByIdAndAccount(routeId, account.getAccountId());
        route.inactive();
        routeRepository.save(route);
    }
}
