package com.dev.torhugo.application.util;

import java.util.Objects;

public class ValidateUtils {
    private ValidateUtils(){}
    public static boolean isNullOrEmpty(final String value){
        return Objects.isNull(value) || Objects.equals(value, "");
    }
}
