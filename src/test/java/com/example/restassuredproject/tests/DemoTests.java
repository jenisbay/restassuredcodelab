package com.example.restassuredproject.tests;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DemoTests {

    @Test
    public void test1(ITestContext context){
        System.out.println("test1()");
        context.setAttribute("method1", "test1()");
    }

    @Test
    public void test2(ITestContext context){
        context.setAttribute("method2", "test2()");
        System.out.println(context.getAttribute("method1"));
        System.out.println(context.getAttribute("method2"));
    }

    @Test
    public void test3(ITestContext context){
        context.setAttribute("method3", "test3()");
        System.out.println(context.getAttribute("method1"));
        System.out.println(context.getAttribute("method2"));
        System.out.println(context.getAttribute("method3"));
    }
}
