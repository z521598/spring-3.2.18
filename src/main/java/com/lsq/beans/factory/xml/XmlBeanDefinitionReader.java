package com.lsq.beans.factory.xml;

import com.lsq.beans.BeanDefinition;
import com.lsq.beans.PropertyValue;
import com.lsq.beans.factory.BeanDefinitionStoreException;
import com.lsq.beans.factory.BeanFactory;
import com.lsq.beans.factory.config.ConstructorArgument;
import com.lsq.beans.factory.config.RuntimeReference;
import com.lsq.beans.factory.config.TypedStringValue;
import com.lsq.beans.factory.support.BeanDefinitionRegistry;
import com.lsq.beans.factory.support.GenericBeanDefinition;
import com.lsq.core.io.Resource;
import com.lsq.util.ClassUtils;
import com.lsq.util.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * Created by Administrator on 2018/7/9.
 */
// 读取xml文件，注册BeanDefinition
public class XmlBeanDefinitionReader {
    public final static String ID_ATTRIBUTE = "id";
    public final static String CLASS_ATTRIBUTE = "class";
    public final static String SCOPE_ATTRIBUTE = "scope";
    public static final String PROPERTY_ELEMENT = "property";
    public static final String REF_ATTRIBUTE = "ref";
    public static final String VALUE_ATTRIBUTE = "value";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String CONSTRUCTOR_ARG_ELEMENT = "constructor-arg";
    public static final String TYPE_ATTRIBUTE = "type";

    private BeanDefinitionRegistry beanDefinitionRegistry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void loadBeanDefinitions(Resource resource) {
        InputStream xmlFileInputStream = null;
        SAXReader saxReader = new SAXReader();
        try {
            xmlFileInputStream = resource.getInputStream();
            Document document = saxReader.read(xmlFileInputStream);
            // <beans>
            Element root = document.getRootElement();
            Iterator<Element> elementIterator = root.elementIterator();
            while (elementIterator.hasNext()) {
                Element element = elementIterator.next();
                String id = element.attributeValue(ID_ATTRIBUTE);
                String className = element.attributeValue(CLASS_ATTRIBUTE);
                String scope = element.attributeValue(SCOPE_ATTRIBUTE);
                BeanDefinition beanDefinition = new GenericBeanDefinition(id, className);
                if (scope != null) {
                    beanDefinition.setScope(scope);
                }
                parseConstructorArguments(element, beanDefinition);
                parsePropertyElement(element, beanDefinition);
                beanDefinitionRegistry.registerBeanDefinition(id, beanDefinition);
            }
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("IOException parsing XML document from " + resource.getDescription(), e);
        } finally {
            // 关闭流
            if (xmlFileInputStream != null) {
                try {
                    xmlFileInputStream.close();
                } catch (IOException e) {

                }
            }
        }
    }

    private void parseConstructorArguments(Element element, BeanDefinition beanDefinition) {
        Iterator iter = element.elementIterator(CONSTRUCTOR_ARG_ELEMENT);
        while (iter.hasNext()) {
            Element ele = (Element) iter.next();
            parseConstructorArgElement(ele, beanDefinition);
        }
    }

    private void parseConstructorArgElement(Element ele, BeanDefinition beanDefinition) {
        String typeAttr = ele.attributeValue(TYPE_ATTRIBUTE);
        String nameAttr = ele.attributeValue(NAME_ATTRIBUTE);
        Object value = parsePropertyValue(ele, null);
        ConstructorArgument.ValueHolder valueHolder = new ConstructorArgument.ValueHolder(value);
        if (StringUtils.hasLength(typeAttr)) {
            valueHolder.setType(typeAttr);
        }
        if (StringUtils.hasLength(nameAttr)) {
            valueHolder.setName(nameAttr);
        }

        beanDefinition.getConstructorArgument().addValueHolder(valueHolder);
    }


    private void parsePropertyElement(Element element, BeanDefinition beanDefinition) {
        Iterator<Element> propertyElementIterator = element.elementIterator(PROPERTY_ELEMENT);
        while (propertyElementIterator.hasNext()) {
            Element propertyElement = propertyElementIterator.next();
            String name = propertyElement.attributeValue(NAME_ATTRIBUTE);
            Object value = parsePropertyValue(propertyElement, name);
            PropertyValue propertyValue = new PropertyValue(name, value);
            beanDefinition.getPropertyValues().add(propertyValue);
        }


    }

    private Object parsePropertyValue(Element propertyElement, String name) {
        String ref = propertyElement.attributeValue(REF_ATTRIBUTE);
        String value = propertyElement.attributeValue(VALUE_ATTRIBUTE);
        if (StringUtils.hasLength(ref)) {
            return new RuntimeReference(ref);
        } else if (StringUtils.hasLength(value)) {
            return new TypedStringValue(value);
        }
        throw new BeanDefinitionStoreException(name + " must specify a ref or value");
    }
}
