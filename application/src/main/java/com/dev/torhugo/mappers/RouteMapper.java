package com.dev.torhugo.mappers;

import com.dev.torhugo.domain.entity.Route;
import com.dev.torhugo.dto.UcBasicRouteDTO;
import com.dev.torhugo.dto.UcCoordinateDTO;

public class RouteMapper {
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
