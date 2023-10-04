package com.board.mapper;

import java.util.List;

import com.board.domain.BoardAttachVO;

public interface BoardAttachMapper {

	public void insert(BoardAttachVO vo);
	
	public void delete(String uuid);
	
	public List<BoardAttachVO> findByBno(Long bno);
		
}

// 첨부파일 추가 작업
//web.xml(업로드 경로, 파일크기)
//servlet context(빈등록)
//첨부파일 라이브러리 Thumbnailator » 0.4.8 등록 pom.xml
//첨부파일 정보가 저장된 db 구축
//
//board테이블과 외래키 설정됨(필수아님)

//첨부파일 테이블에 관련된 VO CLASS 생성
//BoardVO class 관련 코드 추가

//db와 작업할 mapper interface, xml 셋팅



