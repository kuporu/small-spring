package org.hgc.springframework.test;

import org.hgc.springframework.BeanDefinition;
import org.hgc.springframework.BeanFactory;
import org.hgc.springframework.test.bean.UserService;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test_BeanFactory () {

        // 1. 初始化BeanFactory
        BeanFactory beanFactory = new BeanFactory();

        // 2. 注入Bean
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3. 获取Bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
