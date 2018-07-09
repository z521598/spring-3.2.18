package com.lsq.beans.factory.support;

import com.lsq.beans.factory.config.SingletonBeanRegistry;
import com.lsq.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2018/7/9.
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(64);

    public void registerSingletonBean(String beanId, Object singletonObject) {

        Assert.notNull(beanId, "'beanId' must not be null");

        Object oldObject = this.singletonObjects.get(beanId);
        if (oldObject != null) {
            throw new IllegalStateException("Could not register object [" + singletonObject +
                    "] under bean name '" + beanId + "': there is already object [" + oldObject + "] bound");
        }
        this.singletonObjects.put(beanId, singletonObject);

    }

    public Object getSingletonBean(String beanName) {

        return this.singletonObjects.get(beanName);
    }
}
