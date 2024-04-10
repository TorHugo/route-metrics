package com.dev.torhugo.infrastructure.api.mappers;

import com.dev.torhugo.application.dto.UcBasicRouteDTO;
import com.dev.torhugo.infrastructure.api.models.CoordinateDTO;
import com.dev.torhugo.infrastructure.api.models.response.BasicRouteDTO;
import org.springframework.stereotype.Component;

@Component
public class RouteMapper {
    public BasicRouteDTO mapperToBasicRoute(final UcBasicRouteDTO result) {
        return BasicRouteDTO.builder()
                .routeId(result.routeId())
                .accountId(result.accountId())
                .name(result.name())
                .status(result.status())
                .distance(result.distance())
                .coord(new CoordinateDTO(result.initialCoord().latitude(), result.initialCoord().longitude()))
                .lastCoord(new CoordinateDTO(result.lastCoord().latitude(), result.lastCoord().longitude()))
                .active(result.active())
                .createdAt(result.createdAt())
                .updatedAt(result.updatedAt())
                .build();
    }
}
