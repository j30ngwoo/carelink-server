package com.blaybus.server.common;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.ThreadLocalRandom;

public class DateUtils {
    public static LocalDateTime generateRandomCreatedAt() {
        long minDay = LocalDateTime.of(1970, 1, 1, 0, 0).atZone(ZoneId.systemDefault()).toEpochSecond();
        long maxDay = LocalDateTime.of(2024, 12, 1, 0, 0).atZone(ZoneId.systemDefault()).toEpochSecond();

        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDateTime.ofEpochSecond(randomDay, 0, ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now()));
    }
}
