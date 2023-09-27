package com.test.aspect;

import java.sql.Array;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
@Aspect
public class LogAdvice {
	//execution(* com.test.service.SampleService*.*(..)) : execution(리턴타입 메서드주소포함 이름(매개변수))
	@Before("execution(* com.test.service.SampleService*.*(..))")
	public void logBefore() {
		log.info("===================== advice");
	}
	
	@Before("execution(* com.test.service.SampleService*.add(.., String)) && args(str1, str2)")	// .. : 모든 데이터타입, && args : 매개변수 빼와라
	public void logBeforeParam(String str1, String str2) {
		log.info("=================== str1 " + str1 + "  str2 " + str2);
	}
	
	@AfterThrowing(pointcut = "execution(* com.test.service.SampleService*.*(..))", throwing = "exception")
	public void logException(Exception exception) {
		log.info("예외발생 " + exception);
	}
	
	@Around(("execution(* com.test.service.SampleService*.*(..))"))
	public Object logTime(ProceedingJoinPoint pj) {
		long start = System.currentTimeMillis();
		
		log.info("pj " + pj);
		log.info("매개변수 : " + Arrays.toString(pj.getArgs()));
		
		Object result = null;
		
		try {
			result = pj.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		log.info(end - start + "ms");
		
		return result;
	}
	
}
