package com.board.service;

import java.util.List;

import com.board.domain.BoardVO;
import com.board.domain.Paging;

public interface BoardService {

	public BoardVO get(Long bno);
	
	public List<BoardVO> getList(Paging paging);	// 페이징 추가로 변경
	
	public void add(BoardVO vo);
	
	public boolean remove(Long bno);
	
	public boolean modify(BoardVO vo);
	
	public int getTotal(Paging paging);
	
}
