package com.lsq.context.support;

import com.lsq.core.io.Resource;
import com.lsq.core.io.FileSystemResource;

/**
 * Created by Administrator on 2018/7/9.
 */
public class FileSystemXmlApplicationContext extends AbstractApplicationContext {

    public FileSystemXmlApplicationContext(String xmlPath) {
        super(xmlPath);
    }

    @Override
    public Resource getInputStreamByPath(String xmlPath) {
        return new FileSystemResource(xmlPath);
    }
}
