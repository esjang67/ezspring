package com.test.mapper;

import org.apache.ibatis.annotations.Insert;

public interface SampleMapper {
	
	@Insert("insert into tbl_sample1 values(#{data})")
	public int insert(String data);
	
}
