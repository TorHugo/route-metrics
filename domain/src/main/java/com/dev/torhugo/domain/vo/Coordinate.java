package com.dev.torhugo.domain.vo;

import com.dev.torhugo.domain.error.exception.InvalidArgumentError;

public record Coordinate(Double latitude,
                         Double longitude) {
    public Coordinate {
        if (latitude < -90 || latitude > 90) throw new InvalidArgumentError("Invalid latitude!");
        if (longitude < -180 || longitude > 180) throw new InvalidArgumentError("Invalid longitude!");
    }
}

