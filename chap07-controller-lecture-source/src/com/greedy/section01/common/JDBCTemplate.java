package com.greedy.section01.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class JDBCTemplate {
	
	// Connection
	public static Connection getConnection() {
		
		Connection con = null;
		
		Properties prop = new Properties();
		try {
			// 실습 중 틀린 부분! // Application과 구분할 것
			prop.load(new FileReader("config/connection-info.properties"));
			
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			
			Class.forName(driver);
			
			con = DriverManager.getConnection(url, prop);
			
			// 오토 커밋 해제함 // 아래 커밋 관련 내용 추가
			con.setAutoCommit(false);
			
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;

	}
	
	// Close
	public static void close(Connection con) {
		
		try {
			if(con != null && !con.isClosed()) {
				con.close();
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset) {
		
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/* 수동 Commit 이후 */
	public static void commit(Connection con) {
		
		try {
			if(con != null && !con.isClosed()) {
				con.commit();
		     }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection con) {
		
		try {
			if(con != null && !con.isClosed()) {
				con.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
