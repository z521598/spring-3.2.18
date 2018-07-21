package com.lsq.beans.factory.support;

import com.lsq.beans.BeanDefinition;
import com.lsq.beans.factory.xml.XmlBeanDefinitionReader;
import com.lsq.core.io.ClassPathResource;
import com.lsq.service.PetStoreService3;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018/7/21.
 */
public class ConstructorResolverTest {
    DefaultBeanFactory beanFactory;
    XmlBeanDefinitionReader xmlBeanDefinitionReader;

    @Before
    public void setUp() {
        beanFactory = new DefaultBeanFactory();
        xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("beans3.xml"));
    }

    @Test
    public void testAutowireConstructor() throws Exception {
        ConstructorResolver resolver = new ConstructorResolver(beanFactory);
        BeanDefinition bd = beanFactory.getBeanDefinition("petStore3");
        PetStoreService3 petStore = (PetStoreService3) resolver.autowireConstructor(bd);
        Assert.assertEquals(new Integer(3), petStore.getVersion());
        Assert.assertNotNull(petStore.getPetDao());
        Assert.assertNotNull(petStore.getItemDao());

    }
}