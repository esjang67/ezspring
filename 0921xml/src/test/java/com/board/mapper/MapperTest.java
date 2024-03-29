package com.board.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.board.domain.BoardVO;
import com.board.domain.Paging;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MapperTest {

	@Autowired
	private BoardMapper mapper;
	
	@Test
	public void testGetPaging() {
		
		Paging paging = new Paging();
		
//		paging.setAmount(10);
//		paging.setPageNum(2);
		
		paging.setType("CW");
		paging.setKeyword("ㄴㅇㄹ");
		
//		List<BoardVO> list = mapper.getListWithPaging(paging);
//		list.forEach(b -> log.info(b));
		
		log.info(mapper.getTotal(paging));		// 전체갯수(검색어포함)
	}
	
//	@Test
//	public void testGetListAll() {
//		mapper.getListAll().forEach(b -> log.info(b));
//	}
	
//	@Test
//	public void testInsert() {
//		BoardVO vo = new BoardVO();
//		
//		vo.setTitle("test");
//		vo.setContent("test content");
//		vo.setWriter("test");
//		
//		//BoardVO(bno=null, title=test, content=test content, writer=test, regdate=null, updatedate=null)
////		mapper.insert(vo);
//		
//		//BoardVO(bno=9, title=test, content=test content, writer=test, regdate=null, updatedate=null)
//		mapper.insertSelectKey(vo);
//		
//		log.info(vo);
//	}
	
//	@Test
//	public void testRead() {
//		BoardVO vo = mapper.read((Long) 9);
//		
//		log.info(vo);
//	}
	
//	@Test
//	public void testDelete() {
//		int a = mapper.delete((Long) 8);
//		log.info("result " + a);
//	}
	
//	@Test
//	public void testUpdate() {
//		BoardVO vo = new BoardVO();
//		
//		vo.setBno(3);
//		vo.setTitle("Update Test");
//		vo.setContent("Update Test Content");
//		vo.setWriter("test");
//		
//		log.info(mapper.update(vo));
//		
//	}
	
}
