package com.dev.torhugo.infrastructure.api.mappers;

import com.dev.torhugo.application.dto.*;
import com.dev.torhugo.infrastructure.api.models.CoordinateDTO;
import com.dev.torhugo.infrastructure.api.models.response.DetailsAccountDTO;
import com.dev.torhugo.infrastructure.api.models.response.DetailsDTO;
import com.dev.torhugo.infrastructure.api.models.response.DetailsPositionDTO;
import com.dev.torhugo.infrastructure.api.models.response.DetailsRouteDTO;
import org.springframework.stereotype.Component;

@Component
public class DetailsMapper {

    public DetailsDTO mapperFromUseCase(final UcDetailsDTO value) {
        return DetailsDTO.builder()
                .detailsAccount(mapperFromUseCase(value.account()))
                .detailsRoute(mapperFromUseCase(value.route()))
                .build();
    }

    private DetailsAccountDTO mapperFromUseCase(final UcDetailsAccountDTO account) {
        return DetailsAccountDTO.builder()
                .accountId(account.accountId())
                .name(account.name())
                .build();
    }

    private DetailsRouteDTO mapperFromUseCase(final UcDetailsRouteDTO route) {
        return DetailsRouteDTO.builder()
                .routeId(route.routeId())
                .name(route.name())
                .status(route.status())
                .startCoordinate(mapperFromUseCase(route.startCoordinate()))
                .inActive(route.active())
                .position(mapperFromUseCase(route.position()))
                .createdAt(route.createdAt())
                .updatedAt(route.updatedAt())
                .build();
    }

    private DetailsPositionDTO mapperFromUseCase(final UcDetailsPositionDTO position) {
        return DetailsPositionDTO.builder()
                .totalDistance(position.totalDistance())
                .maxVelocity(position.maxVelocity())
                .minVelocity(position.minVelocity())
                .lastCoordinate(mapperFromUseCase(position.lastCoordinate()))
                .createdAt(position.createdAt())
                .build();
    }

    private CoordinateDTO mapperFromUseCase(final UcCoordinateDTO coordinate) {
        return CoordinateDTO.builder()
                .latitude(coordinate.latitude())
                .longitude(coordinate.longitude())
                .time(coordinate.time())
                .build();
    }
}
