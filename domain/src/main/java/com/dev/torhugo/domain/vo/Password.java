package com.dev.torhugo.domain.vo;

import com.dev.torhugo.domain.error.exception.InvalidArgumentError;

public record Password(String value) {
    public Password {
        if (validate(value)) throw new InvalidArgumentError("Invalid password!");
    }
    private boolean validate(String valueInput) {
        return valueInput.length() < 8;
    }
    public String getValue() {
        return value;
    }
}
