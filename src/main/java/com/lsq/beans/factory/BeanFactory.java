package com.lsq.beans.factory;

import com.lsq.beans.BeanDefinition;

/**
 * Created by Administrator on 2018/7/8.
 */
// client入口
public interface BeanFactory {

    Object getBean(String beanId);
}
