package com.undabot.babic.domain.utils;

public final class DateUtilsImpl implements DateUtils {

    private final StringUtils stringUtils;

    public DateUtilsImpl(final StringUtils stringUtils) {
        this.stringUtils = stringUtils;
    }

    @Override
    public long convertISO8601ToTimestamp(final String isoISO8601DateString) {
        // TODO
        return 0L;
    }

    @Override
    public String convertToUserReadableTimestamp(final long timestamp) {
        // TODO
        return "";
    }
}
