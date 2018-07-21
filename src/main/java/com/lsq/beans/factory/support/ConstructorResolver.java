package com.lsq.beans.factory.support;

import com.lsq.beans.BeanDefinition;
import com.lsq.beans.SimpleTypeConverter;
import com.lsq.beans.factory.BeanCreationException;
import com.lsq.beans.factory.config.ConfigurableBeanFactory;
import com.lsq.beans.factory.config.ConstructorArgument;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * Created by Administrator on 2018/7/21.
 */
public class ConstructorResolver {
    private ConfigurableBeanFactory configurableBeanFactory;

    public ConstructorResolver(ConfigurableBeanFactory configurableBeanFactory) {
        this.configurableBeanFactory = configurableBeanFactory;
    }

    public Object autowireConstructor(BeanDefinition beanDefinition) {
        Object[] argsToUse = null;
        Constructor<?> constructorToUse = null;
        ConstructorArgument constructorArgument = beanDefinition.getConstructorArgument();
        List<ConstructorArgument.ValueHolder> args = constructorArgument.getArgumentValues();
        Class beanClass;
        try {
            beanClass = configurableBeanFactory.getBeanClassLoader().loadClass(beanDefinition.getBeanClassName());
        } catch (ClassNotFoundException e) {
            throw new BeanCreationException(beanDefinition.getId(), "Instantiation of bean failed, can't resolve class", e);
        }
        SimpleTypeConverter typeConverter = new SimpleTypeConverter();

        Constructor[] constructors = beanClass.getConstructors();
        for (Constructor constructor : constructors) {
            Class[] parameterTypes = constructor.getParameterTypes();

            if (args.size() != parameterTypes.length) {
                continue;
            }
            argsToUse = new Object[parameterTypes.length];
            BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(configurableBeanFactory);
            boolean result = this.valuesMatchTypes(parameterTypes,
                    constructorArgument.getArgumentValues(),
                    argsToUse,
                    valueResolver,
                    typeConverter);

            if (result) {
                constructorToUse = constructor;
                break;
            }

        }
        if (constructorToUse == null) {
            throw new BeanCreationException(beanDefinition.getId(), "can't find a apporiate constructor");
        }
        try {
            return constructorToUse.newInstance(argsToUse);
        } catch (Exception e) {
            throw new BeanCreationException(beanDefinition.getId(), "can't find a create instance using " + constructorToUse);
        }
    }

    private boolean valuesMatchTypes(Class[] parameterTypes, List<ConstructorArgument.ValueHolder> argumentValues, Object[] argsToUse, BeanDefinitionValueResolver valueResolver, SimpleTypeConverter typeConverter) {
        for (int i = 0; i < parameterTypes.length; i++) {
            ConstructorArgument.ValueHolder valueHolder = argumentValues.get(i);
            //获取参数的值，可能是TypedStringValue, 也可能是RuntimeBeanReference
            Object originalValue = valueHolder.getValue();

            try {
                //获得真正的值
                Object resolvedValue = valueResolver.resolveValueIfNecessary(originalValue);
                //如果参数类型是 int, 但是值是字符串,例如"3",还需要转型
                //如果转型失败，则抛出异常。说明这个构造函数不可用
                Object convertedValue = typeConverter.convertIfNecessary(resolvedValue, parameterTypes[i]);
                //转型成功，记录下来
                argsToUse[i] = convertedValue;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}
