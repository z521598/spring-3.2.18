package com.lsq.beans;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018/7/14.
 */
public class TypeConverterTest {

    @Test
    public void testConvertIfNecessary() throws Exception {
        TypeConverter typeConverter = new SimpleTypeConverter();
        Integer i = typeConverter.convertIfNecessary("3", Integer.class);
        Assert.assertEquals(3, i.intValue());

    }
}