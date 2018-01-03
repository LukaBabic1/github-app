package com.undabot.babic.domain.utils;

public interface DateUtils {

    long convertISO8601ToTimestamp(String isoISO8601DateString);

    String convertToUserReadableTimestamp(long timestamp);
}
