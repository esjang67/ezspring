package com.test.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.config.RootConfig;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class SampleTest {

	@Autowired
	private Restaurant restaurant;
	
	@Autowired
	private Hotel hotel;
	
	@Test
	public void test() {
		
		assertNotNull(hotel);
		
		log.info(hotel);
		log.info("-------------");
		log.info(hotel.getChef());
		
	}
	
}


//의존성 주입(Dependency Injection - DI)
//스프링 컨테이너에서 클래스 관리(root-context.xml)
//참조하면 같은 데이터를 가지고있음! 객체가 1개임
//
//- 생성자
//- setter
//- 필드주입(@Autowired) (!! 주로 이거사용함)
//
//클래스 구현 -> 다른 클래스에서 사용
//- new를 이용해서 객체생성
//- 해당 객체를 생성하는 메서드를 호출해서 만드는 방법
//(싱글톤, A 클래스에서 B 객체를 생성하는 메서드)
