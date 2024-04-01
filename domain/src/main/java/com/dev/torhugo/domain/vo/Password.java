package com.dev.torhugo.domain.vo;

import com.dev.torhugo.domain.error.exception.InvalidArgumentError;

public class Password {
    private final String value;

    public Password(final String password) {
        if (this.validate(password)) throw new InvalidArgumentError("Invalid password!");
        this.value = password;
    }
    private boolean validate(final String valueInput){
        return valueInput.length() < 8;
    }
    public String getValue() {
        return value;
    }
}
