package com.undabot.babic.domain.utils;

public final class StringUtilsImpl implements StringUtils {

    @Override
    public boolean isEmpty(final String text) {
        return text == null || text.isEmpty();
    }
}
