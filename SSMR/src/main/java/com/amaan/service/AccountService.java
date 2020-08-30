package com.amaan.service;

import com.amaan.dao.AccountDao;
import com.amaan.pojo.Account;

import java.util.List;

/**
 * 佛祖保佑，永无BUG
 *
 * @author AMAN
 * SpringTest
 * 2019-12-28 22:00
 */
public interface AccountService {
    /**
     * 查询所有
     * @return
     */
    List<Account> findAllAccount();

    /**
     * 查询一个
     */
    Account findAccountById(Integer accountId);

    List<Account> findAccountByName(String accountName);

    /**
     * 保存账户
     */
    void saveAccount(Account account);

    /**
     * 更新
     */
    void updateAccount(Account account);

    /**
     * 删除
     */
    void deleteAccount(Integer accountId);
}
