package com.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.board.domain.BoardVO;
import com.board.domain.Paging;
import com.board.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor	// 생성자 추가
public class BoardServiceImpl implements BoardService {
	
	private BoardMapper boardMapper;
		
	@Override
	public BoardVO get(Long bno) {
		return boardMapper.read(bno);
	}

	@Override
	public List<BoardVO> getList(Paging paging) {
//		return boardMapper.getListAll();
		return boardMapper.getListWithPaging(paging);
	}

	@Override
	public void add(BoardVO vo) {
		log.info("추가 : " + vo);
		boardMapper.insertSelectKey(vo);
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
	
	
	
}