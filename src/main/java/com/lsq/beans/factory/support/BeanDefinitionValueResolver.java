package com.lsq.beans.factory.support;

import com.lsq.beans.factory.config.RuntimeReference;
import com.lsq.beans.factory.config.TypedStringValue;

/**
 * Created by Administrator on 2018/7/14.
 */
public class BeanDefinitionValueResolver {
    private DefaultBeanFactory defaultBeanFactory;

    public BeanDefinitionValueResolver(DefaultBeanFactory defaultBeanFactory) {
        this.defaultBeanFactory = defaultBeanFactory;
    }


    public Object resolveValueIfNecessary(Object originalValue) {
        if (originalValue instanceof RuntimeReference) {
            RuntimeReference runtimeReference = (RuntimeReference) originalValue;
            return defaultBeanFactory.getBean(runtimeReference.getBeanName());
        } else if (originalValue instanceof TypedStringValue) {
            return ((TypedStringValue) originalValue).getValue();
        } else {
            // TODO
        }
        return null;
    }
}
