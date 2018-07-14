package com.lsq.beans.factory.config;

/**
 * Created by Administrator on 2018/7/14.
 */
public class RuntimeReference {
    private String beanName;

    public RuntimeReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
