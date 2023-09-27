package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.domain.PageReplyDTO;
import com.board.domain.Paging;
import com.board.domain.ReplyVO;
import com.board.mapper.BoardMapper;
import com.board.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyMapper replyMapper;
	
	@Autowired
	private BoardMapper boardMapper;		// 댓글수 표시로 추가됨
	
	@Transactional
	@Override
	public int insert(ReplyVO vo) {
		boardMapper.updateReplyCnt(vo.getBno(), 1);		// 댓글추가
		return replyMapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		return replyMapper.read(rno);
	}
	
	@Transactional
	@Override
	public int remove(Long rno) {
		ReplyVO vo = replyMapper.read(rno);
		boardMapper.updateReplyCnt(vo.getBno(), -1); 			// 댓글 삭제
		return replyMapper.delete(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		return replyMapper.update(vo);
	}

	@Override
	public List<ReplyVO> getList(Paging paging, Long bno) {
		return replyMapper.getListWithPage(paging, bno);
	}

	@Override
	public PageReplyDTO getListPage(Paging paging, Long bno) {
		int cnt = replyMapper.getCountByBno(bno);
		List<ReplyVO> list = replyMapper.getListWithPage(paging, bno);
		return new PageReplyDTO(cnt, list);
	}
	
	
}
