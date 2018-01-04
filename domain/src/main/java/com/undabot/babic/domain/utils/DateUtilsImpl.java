package com.undabot.babic.domain.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class DateUtilsImpl implements DateUtils {

    private static final String ISO_8601_DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final String HUMAN_READABLE_DATE_FORMAT = "EEE, d MMM yyyy, hh:mm aaa";

    private static final long DEFAULT_TIMESTAMP = 0L;

    private final SimpleDateFormat iso8601DateFormat = new SimpleDateFormat(ISO_8601_DATE_FORMAT_PATTERN, Locale.US);
    private final SimpleDateFormat humanReadableDateFormat = new SimpleDateFormat(HUMAN_READABLE_DATE_FORMAT, Locale.US);

    private final StringUtils stringUtils;

    public DateUtilsImpl(final StringUtils stringUtils) {
        this.stringUtils = stringUtils;
    }

    @Override
    public Date convertISO8601ToTimestamp(final String dateString) {
        if (stringUtils.isEmpty(dateString)) {
            return new Date(DEFAULT_TIMESTAMP);
        }

        synchronized (iso8601DateFormat) {
            try {
                return iso8601DateFormat.parse(dateString);

            } catch (final ParseException e) {
                return new Date(DEFAULT_TIMESTAMP);
            }
        }
    }

    @Override
    public String convertToUserReadableTimestamp(final long timestamp) {
        synchronized (humanReadableDateFormat) {
            return humanReadableDateFormat.format(timestamp);
        }
    }
}
