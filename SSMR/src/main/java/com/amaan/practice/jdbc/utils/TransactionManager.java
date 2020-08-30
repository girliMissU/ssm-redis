package com.amaan.practice.jdbc.utils;

import java.sql.SQLException;

/**
 * 和事务管理相关的工具类
 * 包含开启事务，提交事务，回滚事务和释放连接
 */
public class TransactionManager {
	
	private ConnectionUtils connUtils;
	
	public void setConnUtils(ConnectionUtils connUtils) {
		this.connUtils = connUtils;
	}

	public void beginTransaction() {
		try {
			connUtils.getThreadConnection().setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void commit() {
		try {
			connUtils.getThreadConnection().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void rollback() {
		try {
			//System.out.println("业务执行产生异常，进行业务回滚");
			connUtils.getThreadConnection().rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void release() {
		try {
			connUtils.getThreadConnection().close();//还回连接池中
			connUtils.removeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
