package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.board.domain.BoardVO;
import com.board.domain.Paging;

// 실행할 sql문을 만들 추상메서드 인터페이스

public interface BoardMapper {
	
//	@Select("select * from tbl_board where bno > 0 order by bno desc")
	public List<BoardVO> getListAll();
	
	public List<BoardVO> getListWithPaging(Paging paging);
	
	public void insert(BoardVO vo);
	public void insertSelectKey(BoardVO vo);
	
	public BoardVO read(Long bno);
	
//	@Delete("delete from tbl_board where bno = #{bno}
	public int delete(Long bno);
	
	public int update(BoardVO vo);
	
	public int getTotal(Paging paging);
	
}
