package com.lsq.context.support;

import com.lsq.beans.factory.BeanFactory;
import com.lsq.beans.factory.support.DefaultBeanFactory;
import com.lsq.beans.factory.support.XmlBeanDefinitionReader;
import com.lsq.context.ApplicationContext;
import com.lsq.core.io.Resource;
import com.lsq.core.io.support.ClassPathResource;

/**
 * Created by Administrator on 2018/7/9.
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {


    public ClassPathXmlApplicationContext(String xmlPath) {
        super(xmlPath);
    }

    @Override
    public Resource getInputStreamByPath(String xmlPath) {
        return new ClassPathResource(xmlPath, this.getBeanClassLoader());
    }
}
