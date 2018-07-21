package com.lsq;

import com.lsq.beans.BeanFactoryTest;
import com.lsq.beans.TypeConverterTest;
import com.lsq.beans.factory.support.ConstructorResolver;
import com.lsq.beans.factory.support.ConstructorResolverTest;
import com.lsq.context.ApplicationContexTest2;
import com.lsq.context.ApplicationContexTest3;
import com.lsq.context.ApplicationContextTest;
import com.lsq.core.io.ResourceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        ApplicationContextTest.class,
        ApplicationContexTest2.class,
        BeanFactoryTest.class,
        ResourceTest.class,
        TypeConverterTest.class,
        ApplicationContexTest3.class,
        ConstructorResolverTest.class
})
public class V1AllTests {

}
