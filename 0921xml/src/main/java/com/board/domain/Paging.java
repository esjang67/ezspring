package com.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Paging {
	
	private int pageNum;
	private int amount;		// 페이지당 출력할 갯수
	
	// 검색 관련 추가
	private String type;		// 검색조건 (T : title, C : content, W : writer)
	private String keyword;		// 검색 단어
	
	public Paging() {
		pageNum = 1;
		amount = 10;
	}
	
	public Paging(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	// type의 글자 각각 배열로 저장시킴 (TCW, TC, TW, T 등등으로 할수있으므로)
	public String[] getTypeArr() {
		return type == null ? new String[] {} : type.split("");
	}
	
	
}
