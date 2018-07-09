package com.lsq.beans.factory.support;

import com.lsq.beans.BeanDefinition;

/**
 * Created by Administrator on 2018/7/9.
 */
// 注册BeanDefinition接口
public interface BeanDefinitionRegistry {
    BeanDefinition getBeanDefinition(String beanId);

    void registerBeanDefinition(String beanId,BeanDefinition beanDefinition);
}
