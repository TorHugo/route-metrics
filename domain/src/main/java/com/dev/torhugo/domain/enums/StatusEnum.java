package com.dev.torhugo.domain.enums;

import java.util.Arrays;
import java.util.Objects;

public enum StatusEnum {
    REQUESTED("requested"),
    CONFIRMED("confirmed"),
    FINISHED("finished");
    private final String status;

    StatusEnum(final String status) {
        this.status = status;
    }

    public static StatusEnum getStatus(final String status){
        return Arrays.stream(values()).filter(statusEnum -> Objects.equals(statusEnum.status, status))
                .toList().stream().findFirst().orElse(null);
    }
}
