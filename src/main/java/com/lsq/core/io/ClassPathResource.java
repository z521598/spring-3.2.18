package com.lsq.core.io;

import com.lsq.core.io.Resource;
import com.lsq.util.ClassUtils;

import java.io.InputStream;

/**
 * Created by Administrator on 2018/7/9.
 */
public class ClassPathResource implements Resource {
    private ClassLoader classLoader;
    private String xmlPath;

    public ClassPathResource(String xmlPath) {
        this(xmlPath, null);
    }

    public ClassPathResource(String xmlPath, ClassLoader classLoader) {
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
        this.xmlPath = xmlPath;
    }

    @Override
    public InputStream getInputStream() {
        return classLoader.getResourceAsStream(xmlPath);
    }

    @Override
    public String getDescription() {
        return xmlPath;
    }
}
