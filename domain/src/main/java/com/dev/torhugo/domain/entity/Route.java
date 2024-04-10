package com.dev.torhugo.domain.entity;

import com.dev.torhugo.domain.exception.InvalidArgumentException;
import com.dev.torhugo.domain.vo.Coordinate;
import com.dev.torhugo.domain.vo.Status;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Route {
    private final UUID routeId;
    private final UUID accountId;
    private final Double distance;
    private Status status;
    private final String name;
    private final Coordinate initialCoord;
    private final Coordinate lastCoord;
    private boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Route(
            final UUID routeId,
            final UUID accountId,
            final Double distance,
            final String status,
            final String name,
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
        this.status = new Status(status);
        this.name = name;
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
        final var status = "requested";
        final var dateNow = LocalDateTime.now();
        return new Route(
                routeId,
                accountId,
                null,
                status,
                null,
                latitude,
                longitude,
                latitude,
                longitude,
                isActive,
                dateNow,
                null
        );
    }

    public void inactive(){
        if(!this.active) throw new InvalidArgumentException("This account is already inactive.");
        this.active = false;
        this.updatedAt = LocalDateTime.now();
    }

    public void confirm() {
        if(!Objects.equals(this.status.getValue(), "requested")) throw new InvalidArgumentException("Invalid status! Expected status: requested.");
        this.status = new Status("confirmed");
        this.updatedAt = LocalDateTime.now();
    }

    public static Route restore(final UUID routeId,
                                final UUID accountId,
                                final Double distance,
                                final String status,
                                final String name,
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
                name,
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
        return status.getValue();
    }

    public String getName() {
        return name;
    }

    public Coordinate getInitialCoord() {
        return initialCoord;
    }

    public Coordinate getLastCoord() {
        return lastCoord;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
