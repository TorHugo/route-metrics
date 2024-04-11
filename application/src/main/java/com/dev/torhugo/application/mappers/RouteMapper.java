package com.dev.torhugo.application.mappers;

import com.dev.torhugo.application.dto.UcBasicRouteDTO;
import com.dev.torhugo.application.dto.UcCoordinateDTO;
import com.dev.torhugo.domain.entity.Route;

import java.util.List;

public class RouteMapper {
    private RouteMapper(){}
    public static List<UcBasicRouteDTO> mappingToBasic(final List<Route> routes){
        return routes.stream().map(RouteMapper::mappingToBasic).toList();
    }
    public static UcBasicRouteDTO mappingToBasic(final Route route) {
        return new UcBasicRouteDTO(
                route.getRouteId(),
                route.getAccountId(),
                route.getDistance(),
                route.getStatus(),
                route.getName(),
                route.isActive(),
                new UcCoordinateDTO(
                        route.getStartCoordinate().latitude(),
                        route.getStartCoordinate().longitude()
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
