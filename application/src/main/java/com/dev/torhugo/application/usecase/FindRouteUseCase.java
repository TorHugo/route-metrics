package com.dev.torhugo.application.usecase;

import com.dev.torhugo.application.config.DefaultUseCase;
import com.dev.torhugo.application.dto.UcBasicRouteDTO;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.ports.repository.RouteRepository;

import java.util.UUID;

import static com.dev.torhugo.application.mappers.RouteMapper.mappingToBasic;

public class FindRouteUseCase extends DefaultUseCase {
    private final RouteRepository routeRepository;
    private final AccountRepository accountRepository;

    public FindRouteUseCase(final RouteRepository routeRepository,
                            final AccountRepository accountRepository) {
        this.routeRepository = routeRepository;
        this.accountRepository = accountRepository;
    }

    public UcBasicRouteDTO execute(final UUID routeId,
                                   final String email) {
        logger.info("Executing use-case: FindRoute().");
        final var account = accountRepository.findByEmailWithThrow(email);
        final var existingRoute = routeRepository.findByIdAndAccount(routeId, account.getAccountId());
        return mappingToBasic(existingRoute);
    }
}
