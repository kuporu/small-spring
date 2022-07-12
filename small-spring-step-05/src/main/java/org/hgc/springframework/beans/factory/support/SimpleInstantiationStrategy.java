package org.hgc.springframework.beans.factory.support;

import org.hgc.springframework.beans.BeansException;
import org.hgc.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(String name, BeanDefinition beanDefinition, Constructor constructor, Object... args) {
        Class clazz = beanDefinition.getBeanClass();
        try {
            if (constructor != null) {
                return clazz.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
            } else {
                return clazz.newInstance();
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            throw new BeansException("Failed to instantiate [" + clazz.getName() +"]", e);
        }
    }
}
