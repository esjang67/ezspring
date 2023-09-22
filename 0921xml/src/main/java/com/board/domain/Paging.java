package com.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Paging {
	
	private int pageNum;
	private int amount;		// 페이지당 출력할 갯수
	
	public Paging() {
		pageNum = 1;
		amount = 10;
	}
	
	

}
