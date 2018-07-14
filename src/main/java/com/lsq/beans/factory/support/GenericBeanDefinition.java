package com.lsq.beans.factory.support;

import com.lsq.beans.BeanDefinition;
import com.lsq.beans.PropertyValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/8.
 */
public class GenericBeanDefinition implements BeanDefinition {
    String id;
    String className;
    private boolean singleton = true;
    private boolean prototype = false;
    private String scope = SCOPE_DEFAULT;
    private List<PropertyValue> propertyValues = new ArrayList<PropertyValue>();

    public GenericBeanDefinition(String id, String className) {
        this.id = id;
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public boolean isSingleton() {
        return this.singleton;
    }

    public boolean isPrototype() {
        return this.prototype;
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope) || SCOPE_DEFAULT.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);

    }

    @Override
    public String getBeanClassName() {
        return className;
    }

    @Override
    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }
}
