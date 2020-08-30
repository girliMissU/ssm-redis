package com.amaan.practice.proxy;

import java.lang.reflect.Method;

/**
 * 佛祖保佑，永无BUG
 *
 * @author AMAAN
 * SSMR
 * 2020-08-27 14:40
 */
public interface Interceptor {
    /**
     * @param proxy 代理对象
     * @param target 真实对象
     * @param method 方法
     * @param args 运行方法参数
     * @return 返回true，则反射真实对象方法；返回false，调用around方法
     */
    public boolean before(Object proxy, Object target, Method method, Object[] args);
    public void around(Object proxy, Object target, Method method, Object[] args);

    /**
     * 反射真实对象方法或执行around后，调用after
     * @param proxy
     * @param target
     * @param method
     * @param args
     */
    public void after(Object proxy, Object target, Method method, Object[] args);
}
