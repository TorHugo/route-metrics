package com.dev.torhugo.domain.vo;

import com.dev.torhugo.domain.exception.InvalidArgumentException;

import java.util.Objects;

public record HashCode(String value) {
    private static final Integer HASH_SIZE = 6;
    public HashCode {
        if (validate(value)) throw new InvalidArgumentException("Invalid signature for hash code!");
    }

    private boolean validate(final String valueInput) {
        return !Objects.equals(valueInput.length(), HASH_SIZE);
    }


    public String getValue() {
        return value;
    }
}
