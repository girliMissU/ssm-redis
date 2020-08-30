package com.amaan;

import static org.junit.Assert.assertTrue;

import com.amaan.service.HelloService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class HelloServiceTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    @Test
    public void test(){
        /**
         * 1.加载Spring的配置文件
         * 2.取出Bean容器中的实例,单例
         * 3.调用bean方法
         */
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//        HelloService helloService = (HelloService) context.getBean("helloService");
//        helloService.hello();
    }
}
