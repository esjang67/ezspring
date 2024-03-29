package com.board.domain;

import lombok.Getter;

@Getter
public class PageDTO {
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	private int total;
	private Paging paging;
	
	public PageDTO(Paging paging, int total) {
		
		this.paging = paging;
		this.total = total;
		
		endPage = (int)Math.ceil(paging.getPageNum() / 10.0) * 10;
		startPage = endPage - 9;
		
		int realEnd = (int)Math.ceil((double)total / paging.getAmount());
		
		if(endPage > realEnd) {
			endPage = realEnd;			
		}
		
		// 이전, 다음은 블럭기준
		prev = startPage > 1;
		next = endPage < realEnd;
		
	}
	
}
