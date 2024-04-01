package com.dev.torhugo.models;

public record AccountInput(
        String name,
        String email,
        String password
) {
}
