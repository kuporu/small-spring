package org.hgc.springframework.test.proxy;

import java.lang.reflect.Method;

public class SelfCallbackFilter implements net.sf.cglib.proxy.CallbackFilter {
    @Override
    public int accept(Method method) {
        if ("method2".equals(method.getName())) {
            return 1;
        } else if ("method3".equals(method.getName())) {
            return 2;
        } else {
            return 0;
        }
    }
}
