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
    private Status status;
    private String name;
    private final Coordinate startCoordinate;
    private boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Route(
            final UUID routeId,
            final UUID accountId,
            final String status,
            final String name,
            final Double startLatitude,
            final Double startLongitude,
            final LocalDateTime startTime,
            final boolean active,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
    ) {
        this.routeId = routeId;
        this.accountId = accountId;
        this.status = new Status(status);
        this.name = name;
        this.startCoordinate = new Coordinate(startLatitude, startLongitude, startTime);
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
                status,
                null,
                latitude,
                longitude,
                dateNow,
                isActive,
                dateNow,
                null
        );
    }

    public void inactive(){
        if (!Objects.equals(this.status.getValue(), "finished")) throw new InvalidArgumentException("Invalid status!");
        if (!this.active) throw new InvalidArgumentException("This account is already inactive.");
        this.active = false;
        this.updatedAt = LocalDateTime.now();
    }

    public void confirm() {
        if(!Objects.equals(this.status.getValue(), "requested")) throw new InvalidArgumentException("Invalid status! Expected status: requested.");
        this.status = new Status("confirmed");
        this.updatedAt = LocalDateTime.now();
    }

    public void finish(final String name) {
        if(!Objects.equals(this.status.getValue(), "confirmed")) throw new InvalidArgumentException("Invalid status! Expected status: confirmed.");
        this.status = new Status("finished");
        this.updatedAt = LocalDateTime.now();
        this.name = name;
    }

    public static Route restore(final UUID routeId,
                                final UUID accountId,
                                final String status,
                                final String name,
                                final Double startLatitude,
                                final Double startLongitude,
                                final LocalDateTime startTime,
                                final boolean active,
                                final LocalDateTime createdAt,
                                final LocalDateTime updatedAt
    ){
        return new Route(
                routeId,
                accountId,
                status,
                name,
                startLatitude,
                startLongitude,
                startTime,
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

    public String getStatus() {
        return status.getValue();
    }

    public String getName() {
        return name;
    }

    public Coordinate getStartCoordinate() {
        return startCoordinate;
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
