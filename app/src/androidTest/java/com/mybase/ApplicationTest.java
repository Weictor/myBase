package com.mybase;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testApp(){

//        assertEquals();  //断言，第一个值为你想实现的，第二个为实际的
    }

    /**
     * 在所有测试用例执行前调用，可以用来进行初始化操作
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    /**
     * 在所有测试用例执行后调用，可以用来进行资源释放操作
     * @throws Exception
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}