package com.dev.torhugo.domain.vo;

import com.dev.torhugo.domain.exception.InvalidArgumentException;

public record Distance(Double value) {
    public Distance {
        if (validate(value)) throw new InvalidArgumentException("Invalid distance!");
    }

    private boolean validate(final Double value) {
        return value < 0.0;
    }

    public Double getValue() {
        return value;
    }
}
