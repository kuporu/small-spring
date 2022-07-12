package org.hgc.springframework.beans.factory.support;

import org.hgc.springframework.beans.factory.config.SingletonBeanRegist;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegist implements SingletonBeanRegist {

    private Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
}
