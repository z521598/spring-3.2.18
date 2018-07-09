package com.lsq.beans.factory.support;

import com.lsq.beans.BeanDefinition;
import com.lsq.beans.factory.BeanCreationException;
import com.lsq.beans.factory.BeanFactory;
import com.lsq.util.ClassUtils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2018/7/8.
 */
// 权责分离，BeanFactory和BeanDefinitionRegistry是2个权责
public class DefaultBeanFactory implements BeanFactory, BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    @Override
    public BeanDefinition getBeanDefinition(String id) {
        return beanDefinitionMap.get(id);
    }

    @Override
    public void registerBeanDefinition(String beanId, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanId, beanDefinition);
    }

    @Override
    public Object getBean(String id) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(id);
        if (beanDefinition == null) {
            throw new BeanCreationException("Bean Definition does not exist");
        }
        String className = beanDefinition.getClassName();
        try {
            Class cla = ClassUtils.getDefaultClassLoader().loadClass(className);
            return cla.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("create bean for " + className + " failed", e);
        }

    }
}
