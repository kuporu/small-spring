package org.hgc.springframework.test;

import org.hgc.springframework.beans.factory.config.BeanDefinition;
import org.hgc.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.hgc.springframework.beans.factory.support.SimpleInstantiationStrategy;
import org.hgc.springframework.test.bean.UserService;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ApiTest {

    @Test
    public void test_beanFactory() {
        // 1. 初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        使用JDK反射创建对象，默认使用Cglib创建对象
//        beanFactory.setInstantiationStrategy(new SimpleInstantiationStrategy());

        // 2. 注册Bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registryBeanDefinition("userService", beanDefinition);

        // 3. 获取Bean
        UserService userService = (UserService) beanFactory.getBean("userService", "John");
        System.out.println(userService);
    }
}
