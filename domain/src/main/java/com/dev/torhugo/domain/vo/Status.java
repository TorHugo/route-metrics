package com.dev.torhugo.domain.vo;

import com.dev.torhugo.domain.exception.InvalidArgumentException;

import java.util.Objects;

import static com.dev.torhugo.domain.enums.StatusEnum.getStatus;

public record Status(String value) {
    public Status {
        if (validate(value)) throw new InvalidArgumentException("Invalid status of ride!");
    }

    private boolean validate(final String value) {
        return Objects.isNull(getStatus(value));
    }

    public String getValue(){
        return value;
    }
}
