package org.hgc.springframework.beans.factory.support;

import org.hgc.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {

    Object instantiate(String name, BeanDefinition beanDefinition, Constructor constructor, Object...args);
}
