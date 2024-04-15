package com.dev.torhugo.application.usecase;

import com.dev.torhugo.application.config.DefaultUseCase;
import com.dev.torhugo.application.dto.UcFinishRouteDTO;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.ports.repository.RouteRepository;

public class FinishRouteUseCase extends DefaultUseCase {
    private final AccountRepository accountRepository;
    private final RouteRepository routeRepository;

    public FinishRouteUseCase(final AccountRepository accountRepository,
                              final RouteRepository routeRepository) {
        this.accountRepository = accountRepository;
        this.routeRepository = routeRepository;
    }

    public void execute(final String email,
                        final UcFinishRouteDTO input){
        logger.info("Executing use-case: FinishRoute().");
        final var account = accountRepository.findByEmailWithThrow(email);
        final var route = routeRepository.findByIdAndAccount(input.routeId(), account.getAccountId());
        route.finish(input.name());
        routeRepository.save(route);
    }
}
