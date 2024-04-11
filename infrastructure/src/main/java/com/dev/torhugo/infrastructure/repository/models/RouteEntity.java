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
    private String status;
    private String name;
    private Double startLatitude;
    private Double startLongitude;
    private LocalDateTime startTime;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static RouteEntity fromAggregate(final Route route) {
        return new RouteEntity(
                route.getRouteId(),
                route.getAccountId(),
                route.getStatus(),
                route.getName(),
                route.getStartCoordinate().latitude(),
                route.getStartCoordinate().longitude(),
                route.getStartCoordinate().time(),
                route.isActive(),
                route.getCreatedAt(),
                route.getUpdatedAt()
        );
    }

    public static Route toAggregate(final RouteEntity entity) {
        return Route.restore(
                entity.routeId,
                entity.accountId,
                entity.status,
                entity.name,
                entity.startLatitude,
                entity.startLongitude,
                entity.startTime,
                entity.active,
                entity.createdAt,
                entity.updatedAt
        );
    }
}
