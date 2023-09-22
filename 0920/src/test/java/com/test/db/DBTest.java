package com.test.db;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class DBTest {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void dbTest() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","spring","1234");
			log.info(conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
