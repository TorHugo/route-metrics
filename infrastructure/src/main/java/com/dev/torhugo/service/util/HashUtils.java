package com.dev.torhugo.service.util;

import java.util.Random;

public class HashUtils {
    private HashUtils(){}
    private static final Random random = new Random();
    public static String generateUniqueHash(final Integer length){
        final var hash = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomNumber = random.nextInt(10);
            hash.append(randomNumber);
        }
        return hash.toString();
    }
}
