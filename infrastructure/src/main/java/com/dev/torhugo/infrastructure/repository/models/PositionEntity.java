package com.dev.torhugo.infrastructure.repository.models;

import com.dev.torhugo.domain.entity.Position;
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
@Entity(name = "position_tb")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class PositionEntity {
    @Id
    private UUID positionId;
    private UUID routeId;
    private Double latitude;
    private Double longitude;
    private Double velocity;
    private Double distance;
    private LocalDateTime createdAt;

    public static PositionEntity fromAggregate(final Position position){
        return new PositionEntity(
                position.getPositionId(),
                position.getRouteId(),
                position.getCoordinate().latitude(),
                position.getCoordinate().longitude(),
                position.getVelocity(),
                position.getDistance(),
                position.getCreatedAt()
        );
    }

    public static Position toAggregate(final PositionEntity entity){
        return Position.restore(
                entity.positionId,
                entity.routeId,
                entity.latitude,
                entity.longitude,
                entity.velocity,
                entity.distance,
                entity.createdAt
        );
    }
}
