package com.lsq.context;/**
 * Created by Administrator on 2018/7/9.
 */

import com.lsq.context.support.ClassPathXmlApplicationContext;
import com.lsq.service.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

public class ApplicationContextTest {

    @Test
    public void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStore");
        Assert.assertNotNull(petStoreService);
    }
}
