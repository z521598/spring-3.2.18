package com.lsq;

import com.lsq.beans.BeanFactoryTest;
import com.lsq.context.ApplicationContextTest;
import com.lsq.core.io.ResourceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        ApplicationContextTest.class,
        BeanFactoryTest.class,
        ResourceTest.class
})
public class V1AllTests {

}
