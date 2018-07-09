package com.lsq.context.support;

import com.lsq.beans.factory.support.DefaultBeanFactory;
import com.lsq.beans.factory.xml.XmlBeanDefinitionReader;
import com.lsq.context.ApplicationContext;
import com.lsq.core.io.Resource;
import com.lsq.util.ClassUtils;

/**
 * Created by Administrator on 2018/7/9.
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
    private ClassLoader classLoader;
    private DefaultBeanFactory beanFactory;

    public AbstractApplicationContext(String xmlPath) {
        beanFactory = new DefaultBeanFactory();
        beanFactory.setBeanClassLoader(this.getBeanClassLoader());
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinition(getInputStreamByPath(xmlPath));

    }

    public abstract Resource getInputStreamByPath(String xmlPath);

    @Override
    public Object getBean(String beanId) {
        return beanFactory.getBean(beanId);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return classLoader == null ? ClassUtils.getDefaultClassLoader() : classLoader;
    }
}
