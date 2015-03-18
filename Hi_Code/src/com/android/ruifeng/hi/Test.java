package com.android.ruifeng.hi;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.InstrumentationTestCase;

/**
 * User: Zerui
 * Time: 2015/3/14 17:33
 */
public class Test extends ApplicationTestCase<Application>{
    public Test(Class<Application> applicationClass) {
        super(applicationClass);
    }

    public void test(){
        System.out.println("Í¨¹ý²âÊÔ");
    }
}
