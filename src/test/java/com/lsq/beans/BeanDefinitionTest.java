package com.lsq.beans;

import com.lsq.beans.factory.config.ConstructorArgument;
import com.lsq.beans.factory.config.RuntimeReference;
import com.lsq.beans.factory.config.TypedStringValue;
import com.lsq.beans.factory.support.DefaultBeanFactory;
import com.lsq.beans.factory.xml.XmlBeanDefinitionReader;
import com.lsq.core.io.ClassPathResource;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2018/7/14.
 */
public class BeanDefinitionTest {

    @Test
    public void testGetPropertyValues() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("beans2.xml"));
        BeanDefinition beanDefinition = factory.getBeanDefinition("petStore2");
        List<PropertyValue> propertyValueList = beanDefinition.getPropertyValues();
        Assert.assertEquals(4, propertyValueList.size());
        Assert.assertTrue(getObject(propertyValueList, "petDao").getValue() instanceof RuntimeReference);
        Assert.assertTrue(getObject(propertyValueList, "itemDao").getValue() instanceof RuntimeReference);
        Assert.assertTrue(getObject(propertyValueList, "owner").getValue() instanceof TypedStringValue);
    }

    private PropertyValue getObject(List<PropertyValue> propertyValueList, String name) {
        for (PropertyValue pv : propertyValueList) {
            if (pv.getName().equals(name)) {
                return pv;
            }
        }
        return null;
    }

    @Test
    public void testGetConstructorValues() throws Exception {
        DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultBeanFactory);
        reader.loadBeanDefinitions(new ClassPathResource("beans3.xml"));
        BeanDefinition bd = defaultBeanFactory.getBeanDefinition("petStore3");
        ConstructorArgument constructorValues = bd.getConstructorArgument();
        List<ConstructorArgument.ValueHolder> valueHolders = constructorValues.getArgumentValues();
        Assert.assertEquals(3, valueHolders.size());
        Assert.assertEquals("petDao", ((RuntimeReference) valueHolders.get(0).getValue()).getBeanName());
        Assert.assertEquals("3", ((TypedStringValue) valueHolders.get(2).getValue()).getValue());

    }
}
