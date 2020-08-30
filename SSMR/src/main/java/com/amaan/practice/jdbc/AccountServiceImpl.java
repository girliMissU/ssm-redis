package com.amaan.practice.jdbc;

import java.util.List;
import com.amaan.pojo.Account;
import com.amaan.practice.jdbc.utils.TransactionManager;

/**
 * 账户的业务层实现类
 * 
 * 事务控制应该都在业务层
 */
public class AccountServiceImpl implements IAccountService {
	
	private IAccountDao accountDao;
	
	private TransactionManager tsManager;

	public void setTsManager(TransactionManager tsManager) {
		this.tsManager = tsManager;
	}

	public void setAccountDao(IAccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public List<Account> findAllAccount() {
		try {
			//1.开启事务
			tsManager.beginTransaction();
			//2.执行操作
			List<Account> accounts = accountDao.findAllAccount();
			//3.提交事务
			tsManager.commit();
			//4.返回结果
			return accounts;
		}catch(Exception e) {
			//5.回滚事务
			tsManager.rollback();
			throw new RuntimeException();
		}finally {
			//6.释放连接
			tsManager.release();
		}
	}

	@Override
	public Account findAccountById(Integer accountId) {
		try {
			//1.开启事务
			tsManager.beginTransaction();
			//2.执行操作
			Account account = accountDao.findAccountById(accountId);
			//3.提交事务
			tsManager.commit();
			//4.返回结果
			return account;
		}catch(Exception e) {
			//5.回滚事务
			tsManager.rollback();
			throw new RuntimeException();
		}finally {
			//6.释放连接
			tsManager.release();
		}
	}
	
	@Override
	public Account findAccountByName(String accountName) {
		try {
			//1.开启事务
			tsManager.beginTransaction();
			//2.执行操作
			Account account = accountDao.findAccountByName(accountName);
			//3.提交事务
			tsManager.commit();
			//4.返回结果
			return account;
		}catch(Exception e) {
			//5.回滚事务
			tsManager.rollback();
			throw new RuntimeException();
		}finally {
			//6.释放连接
			tsManager.release();
		}
	}

	@Override
	public void saveAccount(Account account) {
		try {
			//1.开启事务
			tsManager.beginTransaction();
			//2.执行操作
			accountDao.saveAccount(account);
			//3.提交事务
			tsManager.commit();
			//4.返回结果

		}catch(Exception e) {
			//5.回滚事务
			tsManager.rollback();
			throw new RuntimeException();
		}finally {
			//6.释放连接
			tsManager.release();
		}
	}

	@Override
	public void updateAccount(Account account) {
		try {
			//1.开启事务
			tsManager.beginTransaction();
			//2.执行操作
			accountDao.updateAccount(account);
			//3.提交事务
			tsManager.commit();
			//4.返回结果

		}catch(Exception e) {
			//5.回滚事务
			tsManager.rollback();
			throw new RuntimeException();
		}finally {
			//6.释放连接
			tsManager.release();
		}
	}

	@Override
	public void deleteAccount(Integer accountId) {
		try {
			//1.开启事务
			tsManager.beginTransaction();
			//2.执行操作
			accountDao.deleteAccount(accountId);
			//3.提交事务
			tsManager.commit();
			//4.返回结果

		}catch(Exception e) {
			//5.回滚事务
			tsManager.rollback();
			throw new RuntimeException();
		}finally {
			//6.释放连接
			tsManager.release();
		}
	}

	@Override
	public void transfer(String sourceName, String targetName, double money) {
		try {
			//1.开启事务
			tsManager.beginTransaction();
			//2.执行操作
			//a.根据名称查询转出账户
			Account source = accountDao.findAccountByName(sourceName);
			//b.根据名称查询转入账户
			Account target = accountDao.findAccountByName(targetName);
			//c.转出账户扣钱
			source.setMoney(source.getMoney()-money);
			//d.转入账户加钱
			target.setMoney(target.getMoney()+money);
			//e.更新转出账户
			accountDao.updateAccount(source);
			//int i=1/0;
			//f.更新转入账户
			accountDao.updateAccount(target);
			//3.提交事务
			tsManager.commit();
			//4.返回结果

		}catch(Exception e) {
			//5.回滚事务
			tsManager.rollback();
			throw new RuntimeException();
		}finally {
			//6.释放连接
			tsManager.release();
		}
		
	}


}
