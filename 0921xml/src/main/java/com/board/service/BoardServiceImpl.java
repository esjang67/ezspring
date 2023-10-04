package com.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.domain.BoardAttachVO;
import com.board.domain.BoardVO;
import com.board.domain.Paging;
import com.board.mapper.BoardAttachMapper;
import com.board.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor	// 생성자 추가
public class BoardServiceImpl implements BoardService {
	
	private BoardMapper boardMapper;
	private BoardAttachMapper boardAttachMapper;
		
	@Override
	public BoardVO get(Long bno) {
		return boardMapper.read(bno);
	}

	@Override
	public List<BoardVO> getList(Paging paging) {
//		return boardMapper.getListAll();
		return boardMapper.getListWithPaging(paging);
	}

	@Transactional
	@Override
	public void add(BoardVO vo) {
		log.info("추가 : " + vo);
		
		boardMapper.insertSelectKey(vo);
		
		// 첨부파일 관련 추가
		if(vo.getAttachList() == null || vo.getAttachList().size() <= 0) {
			return;
		}
		vo.getAttachList().forEach(attach -> {
			attach.setBno(vo.getBno());
			boardAttachMapper.insert(attach);
		});
		
	}

	@Override
	public boolean remove(Long bno) {
		return (boardMapper.delete(bno) == 1);
	}

	@Override
	public boolean modify(BoardVO vo) {
		return boardMapper.update(vo) == 1;
	}

	@Override
	public int getTotal(Paging paging) {
		return boardMapper.getTotal(paging);
	}
	
	// 첨부파일 관련 추가
	@Override
	public List<BoardAttachVO> getAttachList(Long bno) {
		return boardAttachMapper.findByBno(bno);
	}
	
}
