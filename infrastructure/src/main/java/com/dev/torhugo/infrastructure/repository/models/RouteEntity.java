package com.dev.torhugo.infrastructure.repository.models;

import com.dev.torhugo.domain.entity.Route;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Table
@Entity(name = "route_tb")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class RouteEntity {
    @Id
    private UUID routeId;
    private UUID accountId;
    private Double distance;
    private String status;
    private String name;
    private Double initialLatitude;
    private Double initialLongitude;
    private Double lastLatitude;
    private Double lastLongitude;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static RouteEntity fromAggregate(final Route route) {
        return new RouteEntity(
                route.getRouteId(),
                route.getAccountId(),
                route.getDistance(),
                route.getStatus(),
                route.getName(),
                route.getInitialCoord().latitude(),
                route.getInitialCoord().longitude(),
                route.getLastCoord().latitude(),
                route.getLastCoord().longitude(),
                route.isActive(),
                route.getCreatedAt(),
                route.getUpdatedAt()
        );
    }

    public static Route toAggregate(final RouteEntity entity) {
        return Route.restore(
                entity.routeId,
                entity.accountId,
                entity.distance,
                entity.status,
                entity.name,
                entity.initialLatitude,
                entity.initialLongitude,
                entity.lastLatitude,
                entity.lastLongitude,
                entity.active,
                entity.createdAt,
                entity.updatedAt
        );
    }
}
