package com.amaan.practice.jdbc;

import java.util.List;

import com.amaan.pojo.Account;

/**
 * 账户的持久层接口
 */
public interface IAccountDao {
	/**
	 * 查询所有
	 * @return
	 */
	List<Account> findAllAccount();
	
	/**
	 * 查询一个
	 */
	Account findAccountById(Integer accountId);
	
	Account findAccountByName(String accountName);
	
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
