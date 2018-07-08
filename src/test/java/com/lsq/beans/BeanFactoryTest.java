package com.lsq.beans;

import com.lsq.beans.factory.BeanCreationException;
import com.lsq.beans.factory.BeanDefinitionStoreException;
import com.lsq.beans.factory.BeanFactory;
import com.lsq.beans.factory.support.DefaultBeanFactory;
import com.lsq.service.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2018/7/8.
 */
public class BeanFactoryTest {
    @Test
    public void testGetBean() {
        BeanFactory beanFactory = new DefaultBeanFactory("beans.xml");
        BeanDefinition db = beanFactory.getBeanDefinition("petStore");
        String className = db.getClassName();
        Assert.assertEquals("com.lsq.service.PetStoreService", className);
        PetStoreService petStoreService = (PetStoreService) beanFactory.getBean("petStore");
        Assert.assertNotNull(petStoreService);
    }

    @Test(expected = BeanCreationException.class)
    public void testGetInvalidBean() throws Exception {
        BeanFactory beanFactory = new DefaultBeanFactory("beans.xml");
        beanFactory.getBean("invalid");
        beanFactory.getBean("null");
    }

    @Test(expected = BeanDefinitionStoreException.class)
    public void testGetInvalidXML() throws Exception {
        new DefaultBeanFactory("invalid.xml");
    }

}
