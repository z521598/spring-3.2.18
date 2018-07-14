package com.lsq.beans;

/**
 * Created by Administrator on 2018/7/14.
 */
public interface TypeConverter {
    <T> T convertIfNecessary(Object value,Class<T> requireType);
}
