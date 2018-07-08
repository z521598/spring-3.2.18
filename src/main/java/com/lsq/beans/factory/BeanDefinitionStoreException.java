package com.lsq.beans.factory;


import com.lsq.beans.BeansException;

// 读取xml出现的异常
public class BeanDefinitionStoreException extends BeansException {

	public BeanDefinitionStoreException(String msg, Throwable cause) {
		super(msg, cause);
		
	}
	
}
