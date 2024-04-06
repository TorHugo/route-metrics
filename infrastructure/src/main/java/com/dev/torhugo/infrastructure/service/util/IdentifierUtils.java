package com.dev.torhugo.infrastructure.service.util;

import java.util.UUID;

public class IdentifierUtils {
    private IdentifierUtils(){}
    public static UUID generateIdentifier(){
        return UUID.randomUUID();
    }
}
