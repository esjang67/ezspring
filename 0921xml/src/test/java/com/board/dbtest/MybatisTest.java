package com.board.dbtest;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

// db관련 라이브러리 체크함

@RunWith(SpringJUnit4ClassRunner.class)	// 스프링 의존성 주입이 필요하므로 선언
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MybatisTest {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	// DataSource 연결 확인
	@Test
	public void test() {
		try {
			
			SqlSession session = sqlSessionFactory.openSession();
			Connection conn = session.getConnection();
			
			log.info(session);
			log.info(conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
