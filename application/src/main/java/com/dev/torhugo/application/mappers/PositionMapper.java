package com.dev.torhugo.application.mappers;

import com.dev.torhugo.application.dto.UcBasicPositionDTO;
import com.dev.torhugo.application.dto.UcCoordinateDTO;
import com.dev.torhugo.domain.entity.Position;

public class PositionMapper {
    private PositionMapper() {}
    public static UcBasicPositionDTO mappingToBasic(final Position position){
        return new UcBasicPositionDTO(
                position.getPositionId(),
                new UcCoordinateDTO(
                        position.getCoordinate().latitude(),
                        position.getCoordinate().longitude()
                ),
                position.getVelocity(),
                position.getDistance(),
                position.getCreatedAt()
        );
    }
}
