package com.lsq.context;/**
 * Created by Administrator on 2018/7/14.
 */

import com.lsq.context.support.ClassPathXmlApplicationContext;
import com.lsq.service.PetStoreService2;

import org.junit.Assert;
import org.junit.Test;

public class ApplicationContexTest2 {

    @Test
    public void testSetterInject() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans2.xml");
        PetStoreService2 petStoreService2 = (PetStoreService2) applicationContext.getBean("petStore2");
        Assert.assertNotNull(petStoreService2.getItemDao());
        Assert.assertNotNull(petStoreService2.getPetDao());
        Assert.assertEquals("lsq", petStoreService2.getOwner());
        Assert.assertEquals(11,petStoreService2.getNumber());
    }
}
