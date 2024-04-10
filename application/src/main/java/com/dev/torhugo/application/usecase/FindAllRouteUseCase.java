package com.dev.torhugo.application.usecase;

import com.dev.torhugo.application.config.DefaultUseCase;
import com.dev.torhugo.application.dto.UcBasicRouteDTO;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.ports.repository.RouteRepository;

import java.util.List;

import static com.dev.torhugo.application.mappers.RouteMapper.mappingToBasic;

public class FindAllRouteUseCase extends DefaultUseCase {
    private final RouteRepository routeRepository;
    private final AccountRepository accountRepository;

    public FindAllRouteUseCase(final RouteRepository routeRepository,
                               final AccountRepository accountRepository) {
        this.routeRepository = routeRepository;
        this.accountRepository = accountRepository;
    }

    public List<UcBasicRouteDTO> execute(final String email){
        logger.info("Executing use-case: FindAllRoute().");
        final var account = accountRepository.findByEmailWithThrow(email);
        final var routes = routeRepository.findAllByAccount(account.getAccountId());
        return mappingToBasic(routes);
    }
}
