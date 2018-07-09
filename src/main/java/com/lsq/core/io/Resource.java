package com.lsq.core.io;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Administrator on 2018/7/9.
 */
public interface Resource {
    InputStream getInputStream() throws FileNotFoundException;

    String getDescription();
}
