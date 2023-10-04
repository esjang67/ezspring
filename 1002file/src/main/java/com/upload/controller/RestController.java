package com.upload.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.upload.domain.UserVO;

@CrossOrigin(origins = "*")
@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	@GetMapping("/rest")
	public UserVO get() {
		UserVO vo = new UserVO(1, "홍길동", "1234","hong@naver.com");
		return vo;
	}
	
	@GetMapping("/list")
	public List<UserVO> list(){
		
		List<UserVO> list = new ArrayList<UserVO>();
		
		for(int i=0;i<10;i++) {
			UserVO vo = new UserVO(i, "이름" + i, "000" + i, i+"@naver.com");
			list.add(vo);
		}
		
		return list;
	}
}
