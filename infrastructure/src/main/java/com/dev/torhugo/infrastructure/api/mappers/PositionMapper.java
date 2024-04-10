package com.dev.torhugo.infrastructure.api.mappers;

import com.dev.torhugo.application.dto.UcBasicPositionDTO;
import com.dev.torhugo.infrastructure.api.models.CoordinateDTO;
import com.dev.torhugo.infrastructure.api.models.response.BasicPositionDTO;
import org.springframework.stereotype.Component;

@Component
public class PositionMapper {
    public BasicPositionDTO mapperToBasic(final UcBasicPositionDTO result){
        return BasicPositionDTO.builder()
                .positionId(result.positionId())
                .coordinate(new CoordinateDTO(result.coordinate().latitude(), result.coordinate().longitude()))
                .velocity(result.velocity())
                .distance(result.distance())
                .createdAt(result.createdAt())
                .build();
    }
}
