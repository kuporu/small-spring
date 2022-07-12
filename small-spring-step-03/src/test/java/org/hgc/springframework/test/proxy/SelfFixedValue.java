package org.hgc.springframework.test.proxy;

import net.sf.cglib.proxy.FixedValue;

public class SelfFixedValue implements FixedValue {
    @Override
    public Object loadObject() throws Exception {
        return 220;
    }
}
