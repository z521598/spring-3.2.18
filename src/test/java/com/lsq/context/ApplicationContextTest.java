package com.lsq.context;/**
 * Created by Administrator on 2018/7/9.
 */

import com.lsq.context.support.ClassPathXmlApplicationContext;
import com.lsq.context.support.FileSystemXmlApplicationContext;
import com.lsq.service.PetStoreService;
import com.lsq.util.ClassUtils;
import org.junit.Assert;
import org.junit.Test;

public class ApplicationContextTest {

    @Test
    public void testClassPathXmlApplicationContext() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStore");
        Assert.assertNotNull(petStoreService);
    }

    @Test
    public void testFileSystemXmlApplicationContext() {
        String filePath = ClassUtils.getDefaultClassLoader().getResource("beans.xml").getPath();
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext(filePath);
        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStore");
        Assert.assertNotNull(petStoreService);
    }


}
