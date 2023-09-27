package com.board.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageReplyDTO {
	
	private int replyCnt;
	private List<ReplyVO> list;

}
