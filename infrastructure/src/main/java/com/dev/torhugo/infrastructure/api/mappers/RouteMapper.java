package com.dev.torhugo.infrastructure.api.mappers;

import com.dev.torhugo.application.dto.UcBasicRouteDTO;
import com.dev.torhugo.infrastructure.api.models.CoordinateDTO;
import com.dev.torhugo.infrastructure.api.models.response.BasicRouteDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RouteMapper {
    public List<BasicRouteDTO> mapperToBasicRoutes(final List<UcBasicRouteDTO> result){
        return result.stream().map(RouteMapper::mapperToBasicRoute).toList();
    }
    public static BasicRouteDTO mapperToBasicRoute(final UcBasicRouteDTO result) {
        return BasicRouteDTO.builder()
                .routeId(result.routeId())
                .accountId(result.accountId())
                .name(result.name())
                .status(result.status())
                .coord(new CoordinateDTO(result.startCoordinate().latitude(), result.startCoordinate().longitude()))
                .active(result.active())
                .createdAt(result.createdAt())
                .updatedAt(result.updatedAt())
                .build();
    }
}
