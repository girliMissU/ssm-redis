package com.amaan.service.impl;

import com.amaan.service.HelloService;

import java.io.PrintStream;

/**
 * Hello world!
 * @author John
 */
public class HelloServiceImpl implements HelloService {

    private PrintStream stream;
    //构造器注入
    public HelloServiceImpl(PrintStream stream){
        this.stream = stream;
    }
    @Override
    public void hello () {
        stream.println("hello spring !");
    }
}
