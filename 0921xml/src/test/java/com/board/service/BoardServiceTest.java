package com.board.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.board.domain.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTest {

	@Autowired
	private BoardService boardService;	// 인터페이스.
	
//	@Test
//	public void addTest() {
//		BoardVO vo = new BoardVO();
//		
//		vo.setTitle("add test");
//		vo.setContent("add test context");
//		vo.setWriter("add");
//		
//		boardService.add(vo);
//		
//		log.info("추가된 레코드 " + vo);
//	}
	
//	@Test
//	public void getList() {
//		boardService.getList().forEach(b-> log.info(b));
//	}
	
//	@Test
//	public void read() {
//		log.info(boardService.get((Long) 3));
//	}
	
//	@Test
//	public void modifyTest() {
//		
//		BoardVO vo = new BoardVO();
//		
//		vo.setTitle("modify");
//		vo.setContent("modify contect");
//		vo.setWriter("sssss");
//		vo.setBno(5);
//		
//		log.info(boardService.modify(vo));
//	}
	
//	@Test
//	public void removeTest() {
//		log.info(boardService.remove((Long) 4));
//	}
	
	@Test
	public void aaaaTest() {
		
	}
	
}