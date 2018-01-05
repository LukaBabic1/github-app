package com.undabot.babic.domain.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public final class ListUtilsTest {

    private ListUtils listUtils;

    @Before
    public void setUp() throws Exception {
        listUtils = new ListUtilsImpl();
    }

    @Test
    public void testPassingNullList() throws Exception {
        Assert.assertTrue(listUtils.isEmpty(null));
    }

    @Test
    public void textPassingEmptyList() throws Exception {
        Assert.assertTrue(listUtils.isEmpty(new ArrayList<>()));
    }
}
