package com.undabot.babic.domain.utils;

import java.util.Date;

public interface DateUtils {

    Date convertISO8601ToTimestamp(String isoISO8601DateString);

    String convertToUserReadableTimestamp(long timestamp);
}
