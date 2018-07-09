package com.lsq.context.support;

import com.lsq.core.io.Resource;
import com.lsq.core.io.ClassPathResource;

/**
 * Created by Administrator on 2018/7/9.
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {


    public ClassPathXmlApplicationContext(String xmlPath) {
        super(xmlPath);
    }

    @Override
    public Resource getInputStreamByPath(String xmlPath) {
        return new ClassPathResource(xmlPath, this.getBeanClassLoader());
    }
}
