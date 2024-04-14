package com.dev.torhugo.application.usecase;

import com.dev.torhugo.application.config.DefaultUseCase;
import com.dev.torhugo.application.dto.UcDetailsDTO;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.ports.repository.PositionRepository;
import com.dev.torhugo.application.ports.repository.RouteRepository;

import java.util.UUID;

import static com.dev.torhugo.application.mappers.DetailsMapper.mappingFromDomain;

public class FindDetailsRouteUseCase extends DefaultUseCase {

    private final AccountRepository accountRepository;
    private final RouteRepository routeRepository;
    private final PositionRepository positionRepository;

    public FindDetailsRouteUseCase(final AccountRepository accountRepository,
                                   final RouteRepository routeRepository,
                                   final PositionRepository positionRepository) {
        this.accountRepository = accountRepository;
        this.routeRepository = routeRepository;
        this.positionRepository = positionRepository;
    }

    public UcDetailsDTO execute(final String email,
                                final UUID routeId){
        logger.info("Executing use-case: FindDetailsRoute().");
        final var account = accountRepository.findByEmailWithThrow(email);
        final var route = routeRepository.findByIdAndAccount(routeId, account.getAccountId());
        final var position = positionRepository.findPositionByRoute(route.getRouteId());
        return mappingFromDomain(account, route, position);
    }
}
