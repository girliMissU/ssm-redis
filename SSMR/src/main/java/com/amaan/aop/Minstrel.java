package com.amaan.aop;

import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.PrintStream;

/**
 * 佛祖保佑，永无BUG
 *
 * @author AMAN
 * SpringTest
 * 2019-12-30 19:16
 */
@Component
@Aspect
public class Minstrel {

    @Value("#{T(System).out}")
    private PrintStream printStream;

    @Pointcut("execution(* com.amaan.service.impl.*.*(..))")
    public void print(){}

    @Before("print()")
    public void before(){
        printStream.println("before...");
    }

    @After("print()")
    public void after(){
        printStream.println("after...");
    }

    @AfterReturning("print()")
    public void afterReturning(){
        printStream.println("after successful run");
    }

    @AfterThrowing("print()")
    public void afterThrowing(){
        printStream.println("after exception found");
    }

//    @Around("print()")
//    public void sing(){
//        printStream.println("sing with spring aop");
//    }

}
