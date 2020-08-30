package com.amaan.practice.proxy;

/**
 * 佛祖保佑，永无BUG
 *
 * @author AMAAN
 * SSMR
 * 2020-08-27 13:36
 */
public class TestJdkProxy {
    public static void main(String[] args) {
        //JDK动态代理
//        JdkProxy jp = new JdkProxy();
//        HelloService hello = (HelloService) jp.bind(new HelloServiceImpl(System.out));
//        hello.hello();
        //使用拦截器简化动态代理的使用方法
//        HelloService proxy = (HelloService) InterceptorJdkProxy.bind(new HelloServiceImpl(System.out),"com.amaan.practice.proxy.MyInterceptor");
//        proxy.hello();
        //责任链模式，层层代理
//        HelloService proxy = (HelloService) InterceptorJdkProxy.bind(new HelloServiceImpl(System.out),"com.amaan.practice.proxy.MyInterceptor");
//        HelloService proxy2 = (HelloService) InterceptorJdkProxy.bind(proxy,"com.amaan.practice.proxy.MyInterceptor2");
//        HelloService proxy3 = (HelloService) InterceptorJdkProxy.bind(proxy2,"com.amaan.practice.proxy.MyInterceptor3");
        //……层层代理，先执行最内层代理的befor，最后执行最内层代理的after
//        proxy2.hello();
    }
}
