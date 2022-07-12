package org.hgc.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import org.hgc.springframework.beans.BeansException;
import org.hgc.springframework.beans.PropertyValue;
import org.hgc.springframework.beans.PropertyValues;
import org.hgc.springframework.beans.factory.config.BeanDefinition;
import org.hgc.springframework.beans.factory.config.BeanReference;

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
            // 属性填充
            applyPropertyValues(name, bean, beanDefinition);
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

    /**
     * Bean 属性填充
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            if (propertyValues == null)
                return;
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {

                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference) {
                    // A 依赖 B，递归获取 B 的实例化
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                // 属性填充
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName);
        }
    }
}
