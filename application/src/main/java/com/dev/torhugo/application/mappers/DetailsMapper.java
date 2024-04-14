package com.dev.torhugo.application.mappers;

import com.dev.torhugo.application.dto.*;
import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.domain.entity.Position;
import com.dev.torhugo.domain.entity.Route;
import com.dev.torhugo.domain.vo.Coordinate;

public class DetailsMapper {
    private DetailsMapper(){}

    public static UcDetailsDTO mappingFromDomain(final Account account,
                                                 final Route route,
                                                 final Position position){
        return new UcDetailsDTO(
                mappingFromDomain(account),
                mappingFromDomain(route, position)
        );
    }

    public static UcDetailsAccountDTO mappingFromDomain(final Account account){
        return new UcDetailsAccountDTO(
                account.getAccountId(),
                account.getName()
        );
    }

    public static UcDetailsRouteDTO mappingFromDomain(final Route route,
                                                      final Position position){
        return new UcDetailsRouteDTO(
                route.getRouteId(),
                route.getName(),
                route.getStatus(),
                mappingFromDomain(route.getStartCoordinate()),
                route.isActive(),
                mappingFromDomain(position),
                route.getCreatedAt(),
                route.getUpdatedAt()

        );
    }

    private static UcDetailsPositionDTO mappingFromDomain(final Position position) {
        return new UcDetailsPositionDTO(
                position.getDistance(),
                position.getMaxVelocity(),
                position.getMinVelocity(),
                mappingFromDomain(position.getCoordinate()),
                position.getCreatedAt()
        );
    }

    private static UcCoordinateDTO mappingFromDomain(final Coordinate coordinate) {
        return new UcCoordinateDTO(
                coordinate.latitude(),
                coordinate.longitude(),
                coordinate.time()
        );
    }
}
