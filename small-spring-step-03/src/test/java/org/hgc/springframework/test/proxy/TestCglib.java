package org.hgc.springframework.test.proxy;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

/**
 * 生成动态代理类
 */
public class TestCglib {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetObject.class);
        Callback[] cbArr = new Callback[]{NoOp.INSTANCE, new SelfFixedValue(), new SelfInterceptor()};
        enhancer.setCallbacks(cbArr);
        enhancer.setCallbackFilter(new SelfCallbackFilter());
        TargetObject targetObject = (TargetObject) enhancer.create();

        System.out.println(targetObject.method1("para"));
        System.out.println(targetObject.method2(20));
        System.out.println(targetObject.method3(30));
        System.out.println(targetObject);
    }
}
