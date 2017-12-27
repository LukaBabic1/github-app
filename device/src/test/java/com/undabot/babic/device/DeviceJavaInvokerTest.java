package com.undabot.babic.device;

import com.undabot.babic.device.DeviceJavaInvoker;

import org.junit.Test;

public final class DeviceJavaInvokerTest {

    @Test
    public void testInvoking() throws Exception {
        new DeviceJavaInvoker().invoke();
    }
}