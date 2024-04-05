package com.dev.torhugo.domain.entity;

import com.dev.torhugo.domain.exception.InvalidArgumentException;
import com.dev.torhugo.domain.vo.Coordinate;

import java.time.LocalDateTime;
import java.util.UUID;

public class Route {
    private final UUID routeId;
    private final UUID accountId;
    private final Double distance;
    private final String status;
    private final Coordinate initialCoord;
    private final Coordinate lastCoord;
    private final boolean active;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private Route(
            final UUID routeId,
            final UUID accountId,
            final Double distance,
            final String status,
            final Double initialLatitude,
            final Double initialLongitude,
            final Double lastLatitude,
            final Double lastLongitude,
            final boolean active,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
    ) {
        this.routeId = routeId;
        this.accountId = accountId;
        this.distance = distance;
        this.status = status;
        this.initialCoord = new Coordinate(initialLatitude, initialLongitude);
        this.lastCoord = new Coordinate(lastLatitude, lastLongitude);
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Route create(final UUID accountId,
                               final Double latitude,
                               final Double longitude){
        final var routeId = UUID.randomUUID();
        final var isActive = true;
        final var initialStatus = "REQUESTED";
        final var dateNow = LocalDateTime.now();
        return new Route(
                routeId,
                accountId,
                null,
                initialStatus,
                latitude,
                longitude,
                latitude,
                longitude,
                isActive,
                dateNow,
                null
        );
    }

    public Route inactive(final UUID routeId,
                          final UUID accountId,
                          final Double distance,
                          final String status,
                          final Double initialLat,
                          final Double initialLong,
                          final Double lastLat,
                          final Double lastLong,
                          final LocalDateTime createdAt
                          ){
        if(!this.active) throw new InvalidArgumentException("This account is already inactive.");
        final var isActive = false;
        final var dateNow = LocalDateTime.now();
        return new Route(
                routeId,
                accountId,
                distance,
                status,
                initialLat,
                initialLong,
                lastLat,
                lastLong,
                isActive,
                createdAt,
                dateNow
        );
    }

    public static Route restore(final UUID routeId,
                                final UUID accountId,
                                final Double distance,
                                final String status,
                                final Double initialLat,
                                final Double initialLong,
                                final Double lastLat,
                                final Double lastLong,
                                final boolean active,
                                final LocalDateTime createdAt,
                                final LocalDateTime updatedAt
    ){
        return new Route(
                routeId,
                accountId,
                distance,
                status,
                initialLat,
                initialLong,
                lastLat,
                lastLong,
                active,
                createdAt,
                updatedAt
        );
    }

    public UUID getRouteId() {
        return routeId;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public Double getDistance() {
        return distance;
    }

    public String getStatus() {
        return status;
    }

    public Coordinate getInitialCoord() {
        return initialCoord;
    }

    public Coordinate getLastCoord() {
        return lastCoord;
    }

    public boolean getActive() {
        return active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
