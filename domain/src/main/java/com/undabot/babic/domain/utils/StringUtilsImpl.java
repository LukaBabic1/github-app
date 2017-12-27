package com.undabot.babic.domain.utils;

public final class StringUtilsImpl implements StringUtils {

    @Override
    public boolean isEmpty(final String text) {
        return text == null || text.isEmpty();
    }

    @Override
    public String itOrDefault(final String value, final String defaultValue) {
        if (defaultValue == null) {
            throw new IllegalArgumentException("defaultValue == null");
        }

        return isEmpty(value) ? defaultValue : value;
    }
}
