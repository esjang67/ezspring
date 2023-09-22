package com.test.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Component
@Data
@AllArgsConstructor		// 기본 생성자 없이 "모든 멤버변수"를 가지는 생성자를 만듬 [lombok]
//@RequiredArgsConstructor(final이 붙은 멤버변수를 가진 생성자 만듬), @NoArgsConstructor(기본생성자) 등등 종류가 있음 [lombok]
public class Hotel {
	
	private Chef chef;
		
	// 생성자 의존성 주입
//	public Hotel(@Autowired Chef chef) {
//		this.chef = chef;
//	}
	
	// 의존성 주입 안하고싶을때
//	public Hotel(@Autowired(required = false) Chef chef) {
//		this.chef = chef;
//	}
}