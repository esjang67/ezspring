package com.test.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BoardVO {
	
	private String title;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

}
