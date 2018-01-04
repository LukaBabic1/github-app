package com.undabot.babic.domain.utils;

import java.util.List;

public final class ListUtilsImpl implements ListUtils {

    @Override
    public boolean isEmpty(final List<?> list) {
        return list == null || list.isEmpty();
    }
}
