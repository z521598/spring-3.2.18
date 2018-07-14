package com.lsq.beans;

import com.lsq.beans.factory.config.RuntimeReference;
import com.lsq.beans.factory.config.TypedStringValue;
import com.lsq.beans.factory.support.DefaultBeanFactory;
import com.lsq.beans.factory.xml.XmlBeanDefinitionReader;
import com.lsq.core.io.ClassPathResource;
import com.lsq.service.dao.ItemDao;
import com.lsq.service.dao.PetDao;
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

}
