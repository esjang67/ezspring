package com.board.domain;

import lombok.Data;

@Data
public class BoardAttachVO {
	
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean fileType;	// db : 1, 0
	private Long bno;		// board bno
	
}
