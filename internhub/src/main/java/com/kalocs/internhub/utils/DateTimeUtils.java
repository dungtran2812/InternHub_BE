package com.kalocs.internhub.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    private DateTimeUtils() {}
    public static String getTimeStringInTimeZone(String timeZone, long time) {
        ZonedDateTime dateTime = Instant.ofEpochSecond(time)
                .atZone(ZoneId.of(timeZone));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return dateTime.format(formatter);
    }
}
