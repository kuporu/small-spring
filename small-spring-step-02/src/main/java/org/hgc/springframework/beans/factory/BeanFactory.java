package org.hgc.springframework.beans.factory;

import org.hgc.springframework.beans.BeansException;

public interface BeanFactory {
    Object getBean(String name) throws BeansException;
}
