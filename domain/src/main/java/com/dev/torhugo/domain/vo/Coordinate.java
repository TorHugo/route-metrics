package com.dev.torhugo.domain.vo;

import com.dev.torhugo.domain.exception.InvalidArgumentException;

import java.time.LocalDateTime;

public record Coordinate(Double latitude,
                         Double longitude,
                         LocalDateTime time) {
    public Coordinate {
        if (latitude < -90 || latitude > 90) throw new InvalidArgumentException("Invalid latitude!");
        if (longitude < -180 || longitude > 180) throw new InvalidArgumentException("Invalid longitude!");
    }
}

