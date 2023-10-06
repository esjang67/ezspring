package com.study.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class SecurityTest {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void test() {
		
		String sql = "insert into tbl_member_auth(userid, auth) values(?,?)";
		for(int i=0; i<10; i++) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = dataSource.getConnection();
				pstmt = conn.prepareStatement(sql);
				
				if(i<8) {
					pstmt.setString(1, "user" + i);
					pstmt.setString(2, "ROLE_MEMBER");
				} else if(i==8) {
					pstmt.setString(1, "manager" + i);
					pstmt.setString(2, "ROLE_MANAGER");
				} else {
					pstmt.setString(1, "admin" + i);
					pstmt.setString(2, "ROLE_ADMIN");
				}
				pstmt.execute();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					pstmt.close();
					conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
		}
		
//		String sql = "insert into tbl_member(userid, userpw, username) values(?,?,?)";
//		for(int i=0; i<10; i++) {
//			Connection conn = null;
//			PreparedStatement pstmt = null;
//			
//			try {
//				conn = dataSource.getConnection();
//				pstmt = conn.prepareStatement(sql);
//				
//				pstmt.setString(2, passwordEncoder.encode("a" + i));	//비번암호화
//				
//				if(i<8) {
//					pstmt.setString(1, "user" + i);
//					pstmt.setString(3, "사용자" + i);
//				} else if(i==8) {
//					pstmt.setString(1, "manager" + i);
//					pstmt.setString(3, "관리자" + i);
//				} else {
//					pstmt.setString(1, "admin" + i);
//					pstmt.setString(3, "운영자" + i);
//				}
//				pstmt.execute();
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					pstmt.close();
//					conn.close();
//				} catch (Exception e2) {
//					e2.printStackTrace();
//				}
//			}
//			
//		}
	}
	
}


//--create table tbl_member(
//--    userid varchar2(50) not null primary key,
//--    userpw varchar2(100) not null,
//--    username varchar2(100) not null,
//--    regdate date default sysdate,
//--    updatedate date default sysdate,
//--    enabled char(1) default '1'
//--);
//--
//--create table tbl_member_auth(
//--    userid varchar2(50) not null,
//--    auth varchar2(50) not null,
//--    CONSTRAINT fk_member_auth FOREIGN key(userid) REFERENCES tbl_member(userid)
//--);

// 자동로그인 관련 테이블
//	create table persistent_logins (
//	    username varchar(64) not null,
//	    series varchar(64) primary key,
//	    token varchar(64) not null,
//	    last_used timestamp not null
//	);
//
//	select * from persistent_logins;
