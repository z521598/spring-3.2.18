package com.lsq.beans.factory;

import com.lsq.beans.BeanDefinition;

/**
 * Created by Administrator on 2018/7/8.
 */
public interface BeanFactory {

    BeanDefinition getBeanDefinition(String petStore);

    Object getBean(String petStore);
}
