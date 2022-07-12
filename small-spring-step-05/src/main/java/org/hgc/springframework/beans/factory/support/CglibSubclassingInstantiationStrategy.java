package org.hgc.springframework.beans.factory.support;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.hgc.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(String name, BeanDefinition beanDefinition, Constructor constructor, Object... args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(NoOp.INSTANCE);
        if (constructor != null) {
            return enhancer.create(constructor.getParameterTypes(), args);
        } else {
            return enhancer.create();
        }
    }
}
