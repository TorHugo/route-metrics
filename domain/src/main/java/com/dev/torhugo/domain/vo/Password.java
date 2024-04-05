package com.dev.torhugo.domain.vo;

import com.dev.torhugo.domain.exception.InvalidArgumentException;

public record Password(String value) {
    public Password {
        if (validate(value)) throw new InvalidArgumentException("Invalid password!");
    }
    private boolean validate(String valueInput) {
        return valueInput.length() < 8;
    }
    public String getValue() {
        return value;
    }
}
