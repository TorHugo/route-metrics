package com.dev.torhugo.domain.entity;

import com.dev.torhugo.domain.vo.Coordinate;
import com.dev.torhugo.domain.vo.Distance;
import com.dev.torhugo.domain.vo.Velocity;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.dev.torhugo.domain.ds.DistanceCalculator.calculateDistance;
import static com.dev.torhugo.domain.ds.VelocityCalculator.calculateVelocity;

public class Position {
    private final UUID positionId;
    private final UUID routeId;
    private final Coordinate coordinate;
    private final Velocity velocity;
    private final Distance distance;
    private final LocalDateTime createdAt;

    private Position(final UUID positionId,
                     final UUID routeId,
                     final Double latitude,
                     final Double longitude,
                     final Double velocity,
                     final Double distance,
                     final LocalDateTime createdAt) {
        this.positionId = positionId;
        this.routeId = routeId;
        this.coordinate = new Coordinate(latitude, longitude);
        this.velocity = new Velocity(velocity);
        this.distance = new Distance(distance);
        this.createdAt = createdAt;
    }

    public static Position create(final UUID routeId,
                                  final Double latitude,
                                  final Double longitude,
                                  final Double lastLatitude,
                                  final Double lastLongitude,
                                  final LocalDateTime startTime){
        final var uuid = UUID.randomUUID();
        final var dateNow = LocalDateTime.now();
        final var distance = calculateDistance(lastLatitude, lastLongitude, latitude, longitude);
        final var velocity = calculateVelocity(distance, startTime, dateNow);
        return new Position(
                uuid,
                routeId,
                latitude,
                longitude,
                velocity,
                distance,
                dateNow
        );
    }

    public static Position restore(final UUID positionId,
                                   final UUID routeId,
                                   final Double latitude,
                                   final Double longitude,
                                   final Double velocity,
                                   final Double distance,
                                   final LocalDateTime createdAt){
        return new Position(
                positionId,
                routeId,
                latitude,
                longitude,
                velocity,
                distance,
                createdAt
        );
    }

    public UUID getPositionId() {
        return positionId;
    }

    public UUID getRouteId() {
        return routeId;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Double getVelocity() {
        return velocity.getValue();
    }

    public Double getDistance() {
        return distance.getValue();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
