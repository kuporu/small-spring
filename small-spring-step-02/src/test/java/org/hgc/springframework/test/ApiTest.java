package org.hgc.springframework.test;

import org.hgc.springframework.beans.factory.config.BeanDefinition;
import org.hgc.springframework.beans.factory.support.DefaultListableBeanFactory;
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

        // 2. 注册Bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registryBeanDefinition("userService", beanDefinition);

        // 3. 第一次获取Bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        // 4. 第二次获取Bean
        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        userService_singleton.queryUserInfo();

        Assert.assertEquals(userService, userService_singleton);
    }
}
