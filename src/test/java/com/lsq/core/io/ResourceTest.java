package com.lsq.core.io;

import com.lsq.util.ClassUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2018/7/9.
 */
public class ResourceTest {

    @Test
    public void testClassPathXmlGetInputStream() throws Exception {
        Resource resource = new ClassPathResource("beans.xml");
        Assert.assertNotNull(resource.getInputStream());
    }

    @Test
    public void testFileSystemXmlGetInputStream() throws Exception {
        String filePath = ClassUtils.getDefaultClassLoader().getResource("beans.xml").getPath();
        Resource resource = new FileSystemResource(filePath);
        Assert.assertNotNull(resource.getInputStream());
    }

}