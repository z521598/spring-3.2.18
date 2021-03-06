package com.lsq.beans;

import com.lsq.beans.factory.BeanCreationException;
import com.lsq.beans.factory.BeanDefinitionStoreException;
import com.lsq.beans.factory.support.DefaultBeanFactory;
import com.lsq.beans.factory.xml.XmlBeanDefinitionReader;
import com.lsq.core.io.ClassPathResource;
import com.lsq.service.PetStoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 2018/7/8.
 */
public class BeanFactoryTest {
    DefaultBeanFactory beanFactory;
    XmlBeanDefinitionReader xmlBeanDefinitionReader;

    @Before
    public void setUp() {
        beanFactory = new DefaultBeanFactory();
        xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
    }

    @Test
    public void testGetBean() {
        xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("beans.xml"));
        BeanDefinition db = beanFactory.getBeanDefinition("petStore");
        String className = db.getBeanClassName();
        Assert.assertEquals("com.lsq.service.PetStoreService", className);
        PetStoreService petStoreService = (PetStoreService) beanFactory.getBean("petStore");
        Assert.assertNotNull(petStoreService);
    }

    @Test(expected = BeanCreationException.class)
    public void testGetInvalidBean() throws Exception {
        xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("beans.xml"));
        beanFactory.getBean("invalid");
        beanFactory.getBean("null");
    }

    @Test(expected = BeanDefinitionStoreException.class)
    public void testGetInvalidXML() throws Exception {
        xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("invalid.xml"));
    }

    @Test
    public void testSingletonBean() throws Exception {
        xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("beans.xml"));

        PetStoreService petStoreService = (PetStoreService) beanFactory.getBean("petStore");
        PetStoreService petStoreService2 = (PetStoreService) beanFactory.getBean("petStore");
        Assert.assertEquals(petStoreService, petStoreService2);
    }

    @Test
    public void testPrototypeBean() throws Exception {
        xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("beans.xml"));

        PetStoreService petStoreService = (PetStoreService) beanFactory.getBean("petStore2");
        PetStoreService petStoreService2 = (PetStoreService) beanFactory.getBean("petStore2");
        Assert.assertNotEquals(petStoreService, petStoreService2);
    }

}
