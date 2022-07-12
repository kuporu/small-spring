package org.hgc.springframework.beans.factory.support;

import org.hgc.springframework.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
