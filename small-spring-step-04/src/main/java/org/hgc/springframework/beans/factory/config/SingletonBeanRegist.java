package org.hgc.springframework.beans.factory.config;

/**
 * 定义获取单例对象的接口
 */
public interface SingletonBeanRegist {

    Object getSingleton(String beanName);
}
