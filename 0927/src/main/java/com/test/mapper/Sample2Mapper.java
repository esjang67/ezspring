package com.test.mapper;

import org.apache.ibatis.annotations.Insert;

public interface Sample2Mapper {

	@Insert("insert into tbl_sample2 values(#{data})")
	public int insert(String data);
}
