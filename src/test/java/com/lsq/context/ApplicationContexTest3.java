package com.lsq.context;/**
 * Created by Administrator on 2018/7/14.
 */

import com.lsq.context.support.ClassPathXmlApplicationContext;
import com.lsq.service.PetStoreService3;
import org.junit.Assert;
import org.junit.Test;

public class ApplicationContexTest3 {

    @Test
    public void testSetterInject() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans3.xml");
        PetStoreService3 petStoreService3 = (PetStoreService3) applicationContext.getBean("petStore3");
        Assert.assertNotNull(petStoreService3.getItemDao());
        Assert.assertNotNull(petStoreService3.getPetDao());
        Assert.assertEquals(new Integer(3), petStoreService3.getVersion());
    }
}
