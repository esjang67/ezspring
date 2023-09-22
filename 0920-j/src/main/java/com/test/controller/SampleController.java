package com.test.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.test.vo.BoardVO;
import com.test.vo.SampleVO;

@Controller
public class SampleController {
	
	@GetMapping("/a")
	public String aaaa(BoardVO vo) {
		return "model";
	}
	
	@GetMapping("/upload")
	public String upload() {
		return "upload";
	}
	
	@PostMapping("/upload")
	public void uploadTest(ArrayList<MultipartFile> files) {
		for(MultipartFile f : files) {
			System.out.println(f.getOriginalFilename());
			System.out.println(f.getSize());
		}
	}
	
	
	@RequestMapping("/test")		// 기본 get요청
	public void test() {
		System.out.println("== test");
	}
	
	@RequestMapping(value="/test1", method = {RequestMethod.POST})
	public void test1() {
		System.out.println("==test1");
	}
	
	@GetMapping("/test2")
	public void test2() {
		System.out.println("==test2");
	}
	
	@PostMapping("/test2")
	public void test3() {
		System.out.println("==test3");
	}
	
	@GetMapping("/vo")
	public void vo(SampleVO vo) {
		System.out.println(vo);
	}
	
	@GetMapping("/vo2")
	public void vo2(@RequestParam("name") String name, int age) {		// @RequestParam("name") : 파라메터 이름 명시함
		System.out.println(name + " " + age);
	}
	
	@GetMapping("/list")
	public void list(@RequestParam("nums") ArrayList<Integer> nums) {
		for(Integer n : nums) {
			System.out.println(n);
		}
	}
	
	// 날짜형 받기 메서드 또는 vo에서 @을 설정하여도됨
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(sdf, false));
//	}
	
	@GetMapping("/board")
	public void board(BoardVO vo) {
		System.out.println(vo);
	}
	
	// 화면으로 보내기
	// http://localhost:8181/sample/model?name=aaa&title=sss&date=2023-09-11
	@GetMapping("/model")
	public String model(Model model, String name, BoardVO vo) {
		model.addAttribute("name", name);
		// vo : 스프링 객체를 빈에 저장해서 만듬 따로 model에 저장할 필요없음
		return "model";			// model.jsp 로 리턴
	}
	
	@GetMapping("/object")
	@ResponseBody		// 객체직렬화시켜줌
	public SampleVO object() {
		SampleVO vo = new SampleVO();
		vo.setName("kkk");
		vo.setAge(20);
		
		return vo;
	}
	
	// 응답값 헤더포함시켜 보내기
	@GetMapping("/header")
	public ResponseEntity<String> header(){
		String body = "{\"name\" : \"고길동\"}";
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=utf-8");
		
		return new ResponseEntity<String>(body, header, HttpStatus.OK);
	}
	
}
