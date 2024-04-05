package com.dev.torhugo.mapper;

import com.dev.torhugo.domain.entity.Route;
import com.dev.torhugo.models.UcBasicRouteDTO;
import com.dev.torhugo.models.UcCoordinateDTO;
import org.springframework.stereotype.Component;

@Component
public class RouteMapperImpl implements RouteMapper {
    @Override
    public UcBasicRouteDTO mappingToBasic(final Route route) {
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
