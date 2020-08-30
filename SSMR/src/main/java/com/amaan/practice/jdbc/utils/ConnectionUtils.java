package com.amaan.practice.jdbc.utils;

import java.sql.Connection;

import javax.sql.DataSource;

/**
 * SQL Connection 工具类，用于从数据源获取一个连接，将连接与当前线程绑定，使一个线程只有一个能控制事务的对象
 */
public class ConnectionUtils {
	
	private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 获取当前线程上的连接
	 * @return
	 */
	public Connection getThreadConnection() {
		try {
			//1.先从ThreadLocal上获取
			Connection conn = tl.get();
			//2.判断当前线程上是否有连接
			if(conn == null) {
				//3.从数据源中获取一个连接，并且和线程绑定
				conn = dataSource.getConnection();
				tl.set(conn);
			}
			//4.返回当前线程上的连接
			return conn;
		}catch(Exception e) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * 将连接与线程解绑
	 * 由于线程池与数据库连接池的使用，当该线程第一次使用结束后，线程上的conn被关闭，
	 * 如果不解绑，当下一次从线程池中请求线程并获取到该线程时，conn无法使用，因为已经被关闭了
	 */
	public void removeConnection() {
		tl.remove();
	}
}
