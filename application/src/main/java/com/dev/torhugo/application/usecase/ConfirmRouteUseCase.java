package com.dev.torhugo.application.usecase;

import com.dev.torhugo.application.config.DefaultUseCase;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.ports.repository.RouteRepository;

import java.util.UUID;

public class ConfirmRouteUseCase extends DefaultUseCase {
    private final RouteRepository routeRepository;
    private final AccountRepository accountRepository;

    public ConfirmRouteUseCase(final RouteRepository routeRepository,
                               final AccountRepository accountRepository) {
        this.routeRepository = routeRepository;
        this.accountRepository = accountRepository;
    }

    public void execute(final UUID routeId,
                        final String email){
        logger.info("Executing use-case: ConfirmRoute().");
        final var account = accountRepository.findByEmailWithThrow(email);
        final var route = routeRepository.findByIdAndAccount(routeId, account.getAccountId());
        route.confirm();
        routeRepository.save(route);
    }
}
