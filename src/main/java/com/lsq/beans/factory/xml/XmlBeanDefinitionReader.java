package com.lsq.beans.factory.xml;

import com.lsq.beans.BeanDefinition;
import com.lsq.beans.factory.BeanDefinitionStoreException;
import com.lsq.beans.factory.BeanFactory;
import com.lsq.beans.factory.support.BeanDefinitionRegistry;
import com.lsq.beans.factory.support.GenericBeanDefinition;
import com.lsq.core.io.Resource;
import com.lsq.util.ClassUtils;
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


    private BeanDefinitionRegistry beanDefinitionRegistry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void loadBeanDefinition(Resource resource) {
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
}