package com.amaan.practice.proxy;


import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 佛祖保佑，永无BUG
 * 自定义拦截器
 * @author AMAAN
 * SSMR
 * 2020-08-27 14:40
 */
public class MyInterceptor implements Interceptor {
    @Override
    public boolean before(Object proxy, Object target, Method method, Object[] args) {
        System.err.println("反射方法前逻辑");
//        return false;//不反射被代理对象的原有方法
        return true;
    }

    @Override
    public void around(Object proxy, Object target, Method method, Object[] args) {
        System.err.println("取代了被代理对象的方法");
        System.err.println("前置增强：" + method.getName() + ", 参数为: " + Arrays.toString(args));
    }

    @Override
    public void after(Object proxy, Object target, Method method, Object[] args) {
        System.err.println("反射方法后逻辑");
    }
}
