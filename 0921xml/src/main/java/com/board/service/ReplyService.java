package com.board.service;

import java.util.List;

import com.board.domain.Paging;
import com.board.domain.ReplyVO;

public interface ReplyService {
	
	public int insert(ReplyVO vo);
	
	public ReplyVO get(Long rno);
	
	public int remove(Long rno);
	
	public int modify(ReplyVO vo);
	
	public List<ReplyVO> getList(Paging paging, Long bno);
	
}
