package com.amaan.practice.jdbc;

import com.amaan.pojo.Account;

import com.amaan.practice.jdbc.utils.ConnectionUtils;
import java.util.List;
//import org.apache.commons.dbutils.QueryRunner;
//import org.apache.commons.dbutils.handlers.BeanHandler;
//import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 账户的持久层实现类
 * @author Administrator
 * 注释中 使用dbutils的queryrunner执行sql
 * 使用JdbcTemplate执行sql
 */
public class AccountDaoImpl implements IAccountDao {
	
//	private QueryRunner runner;
	
	private ConnectionUtils connUtils;

	public void setConnUtils(ConnectionUtils connUtils) {
		this.connUtils = connUtils;
	}

//	public void setRunner(QueryRunner runner) {
//		this.runner = runner;
//	}
	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
//	public List<Account> findAllAccount() {
//		try {
//			return runner.query(connUtils.getThreadConnection(),
//					"select * from account", new BeanListHandler<Account>(Account.class));
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
	
	public List<Account> findAllAccount() {
		return jdbcTemplate.query("select * from account",
				 new BeanPropertyRowMapper<Account>(Account.class));
	}

	@Override
//	public Account findAccountById(Integer accountId) {
//		try {
//			return runner.query(connUtils.getThreadConnection(),
//					"select * from account where id = ?",
//					new BeanHandler<Account>(Account.class),accountId);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
	
	public Account findAccountById(Integer accountId) {
		List<Account> accounts = jdbcTemplate.query("select * from account where id = ?",
				 new BeanPropertyRowMapper<Account>(Account.class), accountId);
		return accounts.isEmpty()?null:accounts.get(0);
	}
	
	@Override
//	public Account findAccountByName(String accountName) {
//		try {
//			return runner.query(connUtils.getThreadConnection(),
//					"select * from account where name = ?",
//					new BeanHandler<Account>(Account.class),accountName);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
	
	public Account findAccountByName(String accountName) {
		List<Account> accounts = jdbcTemplate.query("select * from account where id = ?",
				 new BeanPropertyRowMapper<Account>(Account.class), accountName);
		if(accounts.isEmpty()) {
			return null;
		}
		if(accounts.size()>1) {
			throw new RuntimeException("结果集不唯一");
		}
		return accounts.get(0);
	}

	@Override
//	public void saveAccount(Account account) {
//		try {
//			runner.update(connUtils.getThreadConnection(),
//					"insert into account(name,money)values(?,?)",account.getName(),account.getMoney());
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
	
	public void saveAccount(Account account) {
		jdbcTemplate.update("insert into account(name,money)values(?,?)",
				account.getName(),account.getMoney());
	}

	@Override
//	public void updateAccount(Account account) {
//		try {
//			runner.update(connUtils.getThreadConnection(),
//					"update account set name=?,money=? where id = ?",
//					account.getName(),account.getMoney(),account.getId());
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
	
	public void updateAccount(Account account) {
		jdbcTemplate.update("update account set name=?,money=? where id = ?",
				account.getName(),account.getMoney(),account.getId());
	}

	@Override
//	public void deleteAccount(Integer accountId) {
//		try {
//			runner.update(connUtils.getThreadConnection(),"delete from account where id = ?",accountId);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
	
	public void deleteAccount(Integer accountId) {
		jdbcTemplate.update("delete from account where id = ?",accountId);
	}

}
