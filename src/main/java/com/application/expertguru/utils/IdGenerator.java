package com.application.expertguru.utils;

import java.util.UUID;

public class IdGenerator {
    public static synchronized String getUUId() {
        UUID uuid= UUID.randomUUID();
        return uuid.toString();
    }
}
