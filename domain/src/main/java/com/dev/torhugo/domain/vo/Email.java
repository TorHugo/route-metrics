package com.dev.torhugo.domain.vo;

import com.dev.torhugo.domain.error.exception.InvalidArgumentError;

public class Email {
    private final String value;

    public Email(final String email) {
        if (this.validate(email)) throw new InvalidArgumentError("Invalid email!");
        this.value = email;
    }
    private boolean validate(final String valueInput){
        return !valueInput.matches("^(.+)@(.+)$");
    }
    public String getValue() {
        return value;
    }
}
