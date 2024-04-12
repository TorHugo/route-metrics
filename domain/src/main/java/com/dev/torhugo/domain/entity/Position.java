package com.dev.torhugo.domain.entity;

import com.dev.torhugo.domain.vo.Coordinate;
import com.dev.torhugo.domain.vo.Distance;
import com.dev.torhugo.domain.vo.Velocity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import static com.dev.torhugo.domain.ds.DistanceCalculator.calculateDistance;
import static com.dev.torhugo.domain.ds.VelocityCalculator.calculateVelocity;

public class Position {
    private final UUID positionId;
    private final UUID routeId;
    private Coordinate coordinate;
    private Velocity maxVelocity;
    private Velocity minVelocity;
    private Distance distance;
    private final LocalDateTime createdAt;

    private Position(final UUID positionId,
                     final UUID routeId,
                     final Double lastLatitude,
                     final Double lastLongitude,
                     final LocalDateTime lastTime,
                     final Double maxVelocity,
                     final Double minVelocity,
                     final Double distance,
                     final LocalDateTime createdAt) {
        this.positionId = positionId;
        this.routeId = routeId;
        this.coordinate = new Coordinate(lastLatitude, lastLongitude, lastTime);
        this.maxVelocity = new Velocity(maxVelocity);
        this.minVelocity = new Velocity(minVelocity);
        this.distance = new Distance(distance);
        this.createdAt = createdAt;
    }

    public static Position create(final UUID routeId,
                                  final Double newLatitude,
                                  final Double newLongitude){
        final var uuid = UUID.randomUUID();
        final var dateNow = LocalDateTime.now();
        final var valueZero = 0.0;
        return new Position(
                uuid,
                routeId,
                newLatitude,
                newLongitude,
                dateNow,
                valueZero,
                valueZero,
                valueZero,
                dateNow
        );
    }

    public void calculateDistanceAndVelocity(final Double newLatitude,
                                             final Double newLongitude) {
        final var dateNow = LocalDateTime.now();
        final var valueZero = 0.0;
        final var newDistance = calculateDistance(this.getCoordinate().latitude(), this.getCoordinate().longitude(), newLatitude, newLongitude);
        final var newVelocity = calculateVelocity(newDistance, this.getCoordinate().time(), dateNow);

        if (Objects.equals(this.maxVelocity.getValue(), valueZero) || newVelocity > this.maxVelocity.getValue())
            this.maxVelocity = new Velocity(newVelocity);
        if (Objects.equals(this.minVelocity.getValue(), valueZero) || newVelocity < this.minVelocity.getValue())
            this.minVelocity = new Velocity(newVelocity);

        if (Objects.equals(this.distance.getValue(), valueZero))
            this.distance = new Distance(newDistance);
        else
            this.distance = new Distance(this.distance.getValue() + newDistance);
    }

    public void updatePosition(final Double newLatitude,
                               final Double newLongitude) {
        final var dateNow = LocalDateTime.now();
        this.coordinate = new Coordinate(newLatitude, newLongitude, dateNow);
    }

    public static Position restore(final UUID positionId,
                                   final UUID routeId,
                                   final Double lastLatitude,
                                   final Double lastLongitude,
                                   final LocalDateTime lastTime,
                                   final Double maxVelocity,
                                   final Double minVelocity,
                                   final Double distance,
                                   final LocalDateTime createdAt){
        return new Position(
                positionId,
                routeId,
                lastLatitude,
                lastLongitude,
                lastTime,
                maxVelocity,
                minVelocity,
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
    public Double getMaxVelocity() {
        return maxVelocity.getValue();
    }
    public Double getMinVelocity() {
        return minVelocity.getValue();
    }
    public Double getDistance() {
        return distance.getValue();
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
