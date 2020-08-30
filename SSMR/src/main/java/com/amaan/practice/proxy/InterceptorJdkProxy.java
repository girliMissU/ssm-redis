package com.amaan.practice.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 佛祖保佑，永无BUG
 *
 * @author AMAAN
 * SSMR
 * 2020-08-27 14:50
 */
public class InterceptorJdkProxy implements InvocationHandler {

    private Object target = null;
    private String interceptorClass = null;

    public InterceptorJdkProxy(Object target, String interceptorClass) {
        this.target = target;
        this.interceptorClass = interceptorClass;
    }

    public static Object bind(Object target, String interceptorClass){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InterceptorJdkProxy(target,interceptorClass));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //未指定拦截器，直接反射原方法
        if(interceptorClass==null){
            return method.invoke(target,args);
        }
        Object res = null;
        Interceptor interceptor = (Interceptor) Class.forName(interceptorClass).newInstance();
        if(interceptor.before(proxy,target,method,args)){
            res = method.invoke(target,args);
        }else{
            interceptor.around(proxy,target,method,args);
        }
        interceptor.after(proxy,target,method,args);
        return res;
    }
}
