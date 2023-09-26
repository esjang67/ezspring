package com.board.RESTController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.board.domain.SampleVO;

import lombok.extern.log4j.Log4j;

// REST API 
// 매핑요청 구분 ::  delete : 삭제요청, get : 조회, post : 요청, put : 수정
// XML, JSON RETURN

@RestController // (@Controller + @ResponseBody)
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
	// 문자열 리턴
//	@GetMapping("/text")
	@GetMapping(value = "/text", produces = "text/plain; charset=utf-8")	// 한글출력
	public String text() {
		return "abc가나12";
	}
	
	// 기본출력 xml, http://localhost:8181/sample/vo.json으로하면 json으로 변환됨
	@GetMapping("/vo")
	public SampleVO vo() {
		return new SampleVO(11, "gildong","ㄴㄴ");
	}
	
	// json 형태로 내보냄
	@GetMapping(value="/vo1", produces = "application/json; charser=utf-8")
	public SampleVO vo1() {
		return new SampleVO(11, "gildong","ㄴㄴ");
	}
	
	@GetMapping("/list")
	public List<SampleVO> list(){
		List<SampleVO> volist = new ArrayList<SampleVO>();
		for(int i=0;i<10;i++) {
			SampleVO vo = new SampleVO(i, "이름"+i, "id"+i);
			volist.add(vo);
		}
		
		return volist;
	}
	
	@GetMapping("/map")
	public Map<String, SampleVO> map(){
		Map<String, SampleVO> map = new HashMap<String, SampleVO>();
		
		map.put("첫번째", new SampleVO(1, "gildong","ㄴㄴ"));
		map.put("두번째", new SampleVO(2, "ㅁㄴㅇㅎㅇ","ㅎㅎ"));
		map.put("세번째", new SampleVO(3, "2ㅈㄷㄱ","ㄴㄴㄴㅇ"));
		
		return map;
	}
	
	@GetMapping("/check")
	public ResponseEntity<SampleVO> check(String name, String id){
		SampleVO vo = new SampleVO(1, name, id);
		
		ResponseEntity<SampleVO> result = null;
		
		if(name.equals("abc")) {
			result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(vo);
		} else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		
		return result;
	}
	
	// http://localhost:8181/sample/pro/aaa/11
	@GetMapping("/pro/{cat}/{pid}")
	public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") int pid) {
		
		return new String[] {"cat : " + cat, "pid : " + pid};
		
	}
	
	// 클라이언트가 보낸 값 받기(postman으로 확인할수 있음)
	@GetMapping("/body")
	public SampleVO body(@RequestBody SampleVO vo) {
		return vo;
	}
	
	
	
}
