package com.undabot.babic.domain.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public final class StringUtilsTest {

    private StringUtils stringUtils;

    @Before
    public void setUp() throws Exception {
        stringUtils = new StringUtilsImpl();
    }

    @Test
    public void testNullStringEvaluatedAsEmpty() throws Exception {
        Assert.assertTrue(stringUtils.isEmpty(null));
    }

    @Test
    public void testEmptyStringEvaluatedAsEmpty() throws Exception {
        Assert.assertTrue(stringUtils.isEmpty(""));
    }

    @Test
    public void testStringEvaluatedAsNotEmpty() throws Exception {
        Assert.assertFalse(stringUtils.isEmpty("not empty"));
    }

    @Test
    public void testReturningDefaultValueWhenOriginalIsNull() throws Exception {
        Assert.assertEquals(stringUtils.itOrDefault(null, "fallback"), "fallback");
    }

    @Test
    public void testReturningDefaultValueWhenOriginalIsEmpty() throws Exception {
        Assert.assertEquals(stringUtils.itOrDefault("", "fallback"), "fallback");
    }

    @Test
    public void testReturningOriginalValueWhenOriginalIsNotEmpty() throws Exception {
        Assert.assertEquals(stringUtils.itOrDefault("original", "fallback"), "original");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowingExceptionForNullFallbackValue() throws Exception {
        stringUtils.itOrDefault(null, null);
    }
}
