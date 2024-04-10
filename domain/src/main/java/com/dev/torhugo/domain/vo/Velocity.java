package com.dev.torhugo.domain.vo;

import com.dev.torhugo.domain.exception.InvalidArgumentException;

public record Velocity(Double value) {
    public Velocity {
        if (validate(value)) throw new InvalidArgumentException("Invalid velocity!");
    }

    private boolean validate(final Double value) {
        return value < 0.0;
    }

    public Double getValue() {
        return value;
    }
}
