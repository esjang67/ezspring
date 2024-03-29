package com.board.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration	// servlet contoller for test
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
						"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
public class BoardContollerTest {

	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mockmvc;
	
	@Before
	public void setup() {
		mockmvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

//	@Test
//	public void testRemove() throws Exception {
//		log.info(
//			mockmvc.perform(MockMvcRequestBuilders.get("/board/remove")
//						.param("bno", "7")			// 문자로 입력할것!
//					)
//				.andReturn()
//				.getModelAndView()
//				.getModelMap()
//			);
//	}
		
//	@Test
//	public void testModify() throws Exception {
//		log.info(
//			mockmvc.perform(MockMvcRequestBuilders.post("/board/modify")
//						.param("bno", "6")
//						.param("title", "컨트롤러 modify test")
//						.param("content", "controller test content")
//						.param("writer", "controller")
//					)
//				.andReturn()
//				.getModelAndView()
//				.getModelMap()
//		);
//		
//	}
	
//	@Test
//	public void testGet() throws Exception {
//		log.info(
//			mockmvc.perform(MockMvcRequestBuilders.get("/board/get")
//						.param("bno", "5")			// 문자로 입력할것!
//					)
//				.andReturn()
//				.getModelAndView()
//				.getModelMap()
//			);
//	}
	
//	@Test
//	public void testAdd() throws Exception {
//		log.info(
//			mockmvc.perform(MockMvcRequestBuilders.post("/board/add")
//						.param("title", "컨트롤러 test")
//						.param("content", "controller test content")
//						.param("writer", "controller")
//					)
//				.andReturn()
//				.getModelAndView()
//				.getModelMap()
//		);
//	}
	
	@Test
	public void testList() throws Exception {
		log.info(
				mockmvc.perform(MockMvcRequestBuilders.get("/board/list")
						.param("pageNum", "3")
						.param("amount", "10")
						)
					.andReturn()
					.getModelAndView()
					.getModelMap()
				);
	}
	
	
}
