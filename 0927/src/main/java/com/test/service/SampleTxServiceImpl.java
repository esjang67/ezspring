package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.mapper.Sample2Mapper;
import com.test.mapper.SampleMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class SampleTxServiceImpl implements SampleTxService {

	@Autowired
	private SampleMapper sampleMapper;
	
	@Autowired
	private Sample2Mapper sample2Mapper;
	
	@Transactional
	@Override
	public void insertData(String data) {
		
		log.info("1번 추가");
		sampleMapper.insert(data);
		log.info("2번 추가");
		sample2Mapper.insert(data);
		
		log.info("완료");
		
	}
	
}
