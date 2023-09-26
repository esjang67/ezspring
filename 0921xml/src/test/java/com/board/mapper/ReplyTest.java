package com.board.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.board.domain.Paging;
import com.board.domain.ReplyVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyTest {
	@Autowired
	private ReplyMapper replyMapper;
	
	private Long[] bnoArr = {260L, 261L, 262L, 263L, 264L};;
	
//	@Test
//	public void list() {
//		List<ReplyVO> list = replyMapper.getListWithPage(new Paging(), bnoArr[0]);
//		
//		list.forEach( b -> log.info(b));
//	}
	
//	@Test
//	public void updateTest() {
//		ReplyVO vo = new ReplyVO();
//		
//		vo.setRno(3L);
//		vo.setReply("수정댓글");
//		
//		replyMapper.update(vo);
//	}
	
//	@Test
//	public void deleteTest() {
//		replyMapper.delete(8L);
//	}
	
//	@Test
//	public void selectTest() {
//		log.info(replyMapper.select(262L));
//	}
	
//	@Test
//	public void readTest() {
//		log.info(replyMapper.read(4L));
//	}
	
//	@Test
//	public void insertTest() {
//		for(int i=0; i<10; i++) {
//			ReplyVO vo = new ReplyVO();
//			
//			vo.setBno(bnoArr[i%5]);
//			vo.setReply("댓글테스트" + i);
//			vo.setReplyer("댓글작성자"+i);
//			
//			replyMapper.insert(vo);
//		}
//	}
	
	
//	@Test
//	public void mapperTest() {
//		log.info(replyMapper);
//	}
}
