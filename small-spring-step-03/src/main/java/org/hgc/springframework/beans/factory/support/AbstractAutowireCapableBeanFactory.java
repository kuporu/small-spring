package org.hgc.springframework.beans.factory.support;

import org.hgc.springframework.beans.BeansException;
import org.hgc.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition, Object...args) throws BeansException {
        Object bean = null;
        try {
            bean = CreateBeanInstance(name, beanDefinition, args);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingleton(name, bean);
        return bean;
    }

    private Object CreateBeanInstance(String name, BeanDefinition beanDefinition, Object[] args) {
        Constructor constructorToUser = null;
        Class<?> clazz = beanDefinition.getBeanClass();
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        for (Constructor ctor: constructors) {
            if (args != null && ctor.getParameterTypes().length == args.length) {
                constructorToUser = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(name, beanDefinition, constructorToUser, args);
    }
}
