package com.lsq.core.io.support;

import com.lsq.core.io.Resource;
import com.lsq.util.ClassUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Administrator on 2018/7/9.
 */
public class FileSystemResource implements Resource {

    private String xmlPath;
    private File file;

    public FileSystemResource(String xmlPath){
        this.xmlPath = xmlPath;
        file = new File(xmlPath);
    }

    // 不能让此类持有一个InputStream对象，会引起资源泄露
    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        return new FileInputStream(file);
    }

    public String getDescription() {
        return "file [" + xmlPath + "]";
    }
}
