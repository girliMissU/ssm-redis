package com.amaan;

import com.amaan.pojo.Account;
import com.amaan.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 佛祖保佑，永无BUG
 * @author AMAAN
 * SSMR
 * 2020-08-28 11:11
 */
//必须junit4.12以上
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:spring-mybatis.xml")
public class AccountTest {

    @Autowired
    public AccountService as;

    @Test
    public void testFind(){
        /**
         * 1.加载Spring的配置文件
         * 2.取出Bean容器中的实例,单例
         * 3.调用bean方法
         */
        System.out.println("find all...");
        List<Account> accounts = as.findAllAccount();
        for (Account account : accounts) {
            System.out.println(account);
        }
        System.out.println("find by id");
        System.out.println(as.findAccountById(1));
    }

    @Test
    public void testFindByName(){
        List<Account> accounts = as.findAccountByName("道");
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testSave(){
        Account account = new Account();
        account.setName("李奕辰");
        account.setMoney(1564.60);
        as.saveAccount(account);
    }

    @Test
    public void testUpdate(){
        Account account = as.findAccountById(1);
        account.setName("凌烟");
        as.updateAccount(account);
    }
}
