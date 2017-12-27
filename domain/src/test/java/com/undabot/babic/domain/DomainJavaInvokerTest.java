package com.undabot.babic.domain;

import org.junit.Test;

public final class DomainJavaInvokerTest {

    @Test
    public void testKotlinInvoker() throws Exception {
        new DomainJavaInvoker().invoke();
    }
}
