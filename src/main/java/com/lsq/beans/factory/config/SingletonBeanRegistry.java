package com.lsq.beans.factory.config;

/**
 * Created by Administrator on 2018/7/9.
 */
public interface SingletonBeanRegistry {
    void registerSingletonBean(String beanId,Object bean);
    Object getSingletonBean(String beanId);
}
