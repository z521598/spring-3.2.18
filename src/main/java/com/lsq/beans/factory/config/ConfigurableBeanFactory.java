package com.lsq.beans.factory.config;

import com.lsq.beans.factory.BeanFactory;

/**
 * Created by Administrator on 2018/7/9.
 */
public interface ConfigurableBeanFactory extends BeanFactory{
    void setBeanClassLoader(ClassLoader classLoader);

    ClassLoader getBeanClassLoader();
}
