package com.amaan.service.impl;

import com.amaan.dao.AccountDao;
import com.amaan.pojo.Account;
import com.amaan.service.AccountService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 佛祖保佑，永无BUG
 * @Transactional底层是SpringAOP动态代理，因此对于静态方法和非public方法失效，且自调用也会失效
 * @author AMAAN
 * SpringTest
 * 2019-12-28 22:02
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
//    @Qualifier("accountDao")
    private AccountDao accountDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<Account> findAllAccount() {
        return accountDao.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Account findAccountById(Integer accountId) {
        return accountDao.findOneById(accountId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<Account> findAccountByName(String accountName) {
        return accountDao.findAnyByName(accountName);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void saveAccount(Account account) {
        try {
            accountDao.insert(account);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public void updateAccount(Account account) {
        accountDao.update(account);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public void deleteAccount(Integer accountId) {
        accountDao.deleteById(accountId);
    }
}
