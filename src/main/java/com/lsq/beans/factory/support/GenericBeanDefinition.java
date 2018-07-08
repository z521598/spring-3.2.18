package com.lsq.beans.factory.support;

import com.lsq.beans.BeanDefinition;

/**
 * Created by Administrator on 2018/7/8.
 */
public class GenericBeanDefinition implements BeanDefinition {
    String id;
    String className;

    public GenericBeanDefinition(String id, String className) {
        this.id = id;
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
