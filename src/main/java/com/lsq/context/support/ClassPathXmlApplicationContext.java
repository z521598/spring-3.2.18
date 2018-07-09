package com.lsq.context.support;

import com.lsq.beans.factory.BeanFactory;
import com.lsq.beans.factory.support.DefaultBeanFactory;
import com.lsq.beans.factory.support.XmlBeanDefinitionReader;
import com.lsq.context.ApplicationContext;

/**
 * Created by Administrator on 2018/7/9.
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {

    private DefaultBeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String xmlPath) {
        beanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinition(xmlPath);
    }

    @Override
    public Object getBean(String beanId) {
        return beanFactory.getBean(beanId);
    }
}
