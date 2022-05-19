package com.nubigo.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

	// 1. Connection 객체 생성 (DB 접속) 후 해당 Connection 객체를 반환하는 메소드	
	public static Connection getConnection() {
		
		Connection conn = null;
		
		/*
		// 1) JDBC Driver 등록
		try {
			
			Class.forName("oracle.jdbcdriver.OracleDriver");
			
			// 2) DB 와의 접속정보를 갖고있는 Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SERVER", "SERVER");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
		// 정적 코딩 방식
		
		// 동적 코딩 방식
		Properties prop = new Properties();
		
		try {
			// 배포되는 시점에서는 src 폴더가 WebContent 폴더 내에 없기 때문에
			// src 를 시작점으로 잡으면 오류 발생 => classes 폴더를 시작점으로 잡아 줄 것
			String fileName = JDBCTemplate.class.getResource("/sql/driver/driver.properties").getPath();
			
			// C:\06_Web-workspace2\JSP_Project\WebContent\WEB-IINF\classes\sql\driver\driver.properties
			
			prop.load(new FileInputStream(fileName));
			
			Class.forName(prop.getProperty("driver"));
			
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	// 2. 전달받은 Connection 객체를 가지고 commit 해주는 메소드
	public static void commit(Connection conn) {
		try {
			if((conn != null) && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 3. 전달받은 Connection 객체를 가지고 rollback 해주는 메소드
	public static void rollback(Connection conn) {
		try {
			if((conn != null) && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 4. 전달받은 Connection 객체를 반납시켜주는 메소드
	public static void close(Connection conn) {
		try {
			if((conn != null) && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 5. 전달받은 Statement 객체를 반납시켜주는 메소드
	// => 다형성에 의해 자식클래스인 PreparedStatement 도 반납 가능
	public static void close(Statement stmt) {
		try {
			if((stmt != null) && !stmt.isClosed() ) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 6. 전달받은 ResultSet 객체를 반납시켜주는 메소드
	public static void close(ResultSet rset) {
		try {
			if((rset != null) && !rset.isClosed() ) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
