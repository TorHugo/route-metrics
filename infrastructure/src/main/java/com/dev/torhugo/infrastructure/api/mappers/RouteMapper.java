package com.dev.torhugo.infrastructure.api.mappers;

import com.dev.torhugo.application.dto.UcBasicRouteDTO;
import com.dev.torhugo.application.dto.UcFinishRouteDTO;
import com.dev.torhugo.application.dto.UcRouteDTO;
import com.dev.torhugo.infrastructure.api.models.CoordinateDTO;
import com.dev.torhugo.infrastructure.api.models.request.CreateRouteDTO;
import com.dev.torhugo.infrastructure.api.models.request.FinishRouteDTO;
import com.dev.torhugo.infrastructure.api.models.response.BasicRouteDTO;
import com.dev.torhugo.infrastructure.api.models.response.RouteCreateDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class RouteMapper {
    public List<BasicRouteDTO> mapperFromUseCase(final List<UcBasicRouteDTO> result){
        return result.stream().map(this::mapperFromUseCase).toList();
    }
    public BasicRouteDTO mapperFromUseCase(final UcBasicRouteDTO result) {
        return BasicRouteDTO.builder()
                .routeId(result.routeId())
                .accountId(result.accountId())
                .name(result.name())
                .status(result.status())
                .coord(new CoordinateDTO(result.startCoordinate().latitude(), result.startCoordinate().longitude(), result.createdAt()))
                .active(result.active())
                .createdAt(result.createdAt())
                .updatedAt(result.updatedAt())
                .build();
    }

    public UcFinishRouteDTO mapperToUseCase(final FinishRouteDTO request) {
        return new UcFinishRouteDTO(
                request.routeId(),
                request.name()
        );
    }

    public UcRouteDTO mapperToUseCase(final CreateRouteDTO request) {
        return new UcRouteDTO(
                request.accountId(),
                request.coordinate().latitude(),
                request.coordinate().longitude()
        );
    }

    public RouteCreateDTO mapperFromUseCase(final UUID routeId) {
        return new RouteCreateDTO(
                routeId
        );
    }
}
