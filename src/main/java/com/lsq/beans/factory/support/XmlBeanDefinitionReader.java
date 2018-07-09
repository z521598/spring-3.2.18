package com.lsq.beans.factory.support;

import com.lsq.beans.BeanDefinition;
import com.lsq.beans.factory.BeanDefinitionStoreException;
import com.lsq.beans.factory.BeanFactory;
import com.lsq.util.ClassUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

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

    private BeanDefinitionRegistry beanDefinitionRegistry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void loadBeanDefinition(String xmlPath) {
        InputStream xmlFileInputStream = ClassUtils.getDefaultClassLoader().getResourceAsStream(xmlPath);
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(xmlFileInputStream);
            // <beans>
            Element root = document.getRootElement();
            Iterator<Element> elementIterator = root.elementIterator();
            while (elementIterator.hasNext()) {
                Element element = elementIterator.next();
                String id = element.attributeValue(ID_ATTRIBUTE);
                String className = element.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition beanDefinition = new GenericBeanDefinition(id, className);
                beanDefinitionRegistry.registerBeanDefinition(id,beanDefinition);
            }
        } catch (DocumentException e) {
            throw new BeanDefinitionStoreException("IOException parsing XML document from " + xmlPath, e);
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
}
