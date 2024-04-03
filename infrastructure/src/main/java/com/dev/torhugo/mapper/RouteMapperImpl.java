package com.dev.torhugo.mapper;

import com.dev.torhugo.domain.entity.Route;
import com.dev.torhugo.models.BasicRouteDTO;
import com.dev.torhugo.models.CoordinateDTO;
import org.springframework.stereotype.Component;

@Component
public class RouteMapperImpl implements RouteMapper {
    @Override
    public BasicRouteDTO mappingToBasic(final Route route) {
        return new BasicRouteDTO(
                route.getRouteId(),
                route.getAccountId(),
                route.getDistance(),
                route.getStatus(),
                route.getActive(),
                new CoordinateDTO(
                        route.getInitialCoord().latitude(),
                        route.getInitialCoord().longitude()
                ),
                new CoordinateDTO(
                        route.getLastCoord().latitude(),
                        route.getLastCoord().longitude()
                ),
                route.getCreatedAt(),
                route.getUpdatedAt()
        );
    }
}
