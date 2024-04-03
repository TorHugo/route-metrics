package com.dev.torhugo.domain.vo;

import com.dev.torhugo.domain.error.exception.InvalidArgumentError;

public record Email(String value) {
    public Email {
        if (validate(value)) throw new InvalidArgumentError("Invalid email!");
    }

    private boolean validate(final String valueInput) {
        return !valueInput.matches("^(.+)@(.+)$");
    }

    public String getValue() {
        return value;
    }
}