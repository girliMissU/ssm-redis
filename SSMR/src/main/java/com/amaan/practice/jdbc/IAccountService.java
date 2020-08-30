package com.amaan.practice.jdbc;

import java.util.List;

import com.amaan.pojo.Account;

/**
 * 账户的业务层接口
 * @author Administrator
 *
 */
public interface IAccountService {
	
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
	
	/**
	 * 转账
	 */
	void transfer(String sourceName, String targetName, double money);
}
