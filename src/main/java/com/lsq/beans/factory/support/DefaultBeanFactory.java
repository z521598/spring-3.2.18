package com.lsq.beans.factory.support;

import com.lsq.beans.BeanDefinition;
import com.lsq.beans.PropertyValue;
import com.lsq.beans.SimpleTypeConverter;
import com.lsq.beans.TypeConverter;
import com.lsq.beans.factory.BeanCreationException;
import com.lsq.beans.factory.config.ConfigurableBeanFactory;
import com.lsq.beans.factory.config.RuntimeReference;
import com.lsq.util.ClassUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2018/7/8.
 */
// 权责分离，BeanFactory和BeanDefinitionRegistry是2个权责
public class DefaultBeanFactory extends DefaultSingletonBeanRegistry
        implements BeanDefinitionRegistry, ConfigurableBeanFactory {

    private ClassLoader classLoader;
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
    private TypeConverter typeConverter = new SimpleTypeConverter();

    @Override
    public BeanDefinition getBeanDefinition(String id) {
        return beanDefinitionMap.get(id);
    }

    @Override
    public void registerBeanDefinition(String beanId, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanId, beanDefinition);
    }

    @Override
    public Object getBean(String beanId) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanId);
        if (beanDefinition == null) {
            throw new BeanCreationException("Bean Definition does not exist");
        }
        if (beanDefinition.isSingleton()) {
            // FIXME 线程不安全
            Object bean = getSingletonBean(beanId);
            if (bean == null) {
                bean = createBean(beanDefinition);
                registerSingletonBean(beanId, bean);
            }

            return bean;
        }
        return createBean(beanDefinition);


    }

    private Object createBean(BeanDefinition beanDefinition) {
        Object bean = instanceBean(beanDefinition);
        populateBean(bean, beanDefinition);
        return bean;
    }

    private void populateBean(Object bean, BeanDefinition beanDefinition) {
        List<PropertyValue> propertyValues = beanDefinition.getPropertyValues();
        if (propertyValues == null || propertyValues.isEmpty()) {
            return;
        }
        try {
            BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(this);
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            for (PropertyValue propertyValue : propertyValues) {

                String propertyName = propertyValue.getName();
                Object originalValue = propertyValue.getValue();
                Object resolveValue = valueResolver.resolveValueIfNecessary(originalValue);

                for (PropertyDescriptor pd : pds) {
                    if (pd.getName().equals(propertyName)) {
                        //  pd.getPropertyType() 可获取到参数的具体的类型信息
                        Object convertValue = typeConverter.convertIfNecessary(resolveValue, pd.getPropertyType());
                        pd.getWriteMethod().invoke(bean, convertValue);
                    }
                }

            }
        } catch (Exception e) {
            throw new BeanCreationException("Failed to obtain BeanInfo for class [" + beanDefinition.getBeanClassName() + "]", e);
        }

    }

    private Object instanceBean(BeanDefinition beanDefinition) {
        String className = beanDefinition.getBeanClassName();
        try {
            Class cla = getBeanClassLoader().loadClass(className);
            return cla.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("create bean for " + className + " failed", e);
        }
    }


    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return classLoader == null ? ClassUtils.getDefaultClassLoader() : classLoader;
    }
}
