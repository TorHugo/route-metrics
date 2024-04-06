package com.dev.torhugo.application.mappers;

import com.dev.torhugo.application.dto.UcBasicRouteDTO;
import com.dev.torhugo.application.dto.UcCoordinateDTO;
import com.dev.torhugo.domain.entity.Route;

public class RouteMapper {
    private RouteMapper(){}
    public static UcBasicRouteDTO mappingToBasic(final Route route) {
        return new UcBasicRouteDTO(
                route.getRouteId(),
                route.getAccountId(),
                route.getDistance(),
                route.getStatus(),
                route.getActive(),
                new UcCoordinateDTO(
                        route.getInitialCoord().latitude(),
                        route.getInitialCoord().longitude()
                ),
                new UcCoordinateDTO(
                        route.getLastCoord().latitude(),
                        route.getLastCoord().longitude()
                ),
                route.getCreatedAt(),
                route.getUpdatedAt()
        );
    }
}
