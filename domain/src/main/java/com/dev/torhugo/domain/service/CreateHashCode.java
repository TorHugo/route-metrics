package com.dev.torhugo.domain.service;

import java.util.Random;

public class CreateHashCode {
    private CreateHashCode(){}
    private static final Integer LENGTH_RULE_OF_HASH_CODE = 6;
    private static final Random random = new Random();
    public static String create() {
        final var hash = new StringBuilder();
        for (int i = 0; i < LENGTH_RULE_OF_HASH_CODE; i++) {
            int randomNumber = random.nextInt(10);
            hash.append(randomNumber);
        }
        return hash.toString();
    }
}
