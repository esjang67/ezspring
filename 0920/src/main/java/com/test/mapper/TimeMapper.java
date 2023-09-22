package com.test.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	
	@Select("select sysdate from dual")
	public String getTime();
	
	public String getTime2();
	// xml 만듬: resources폴더안에 com/test/mapper에 인터페이스랑 같은 파일명으로 작성함
}
