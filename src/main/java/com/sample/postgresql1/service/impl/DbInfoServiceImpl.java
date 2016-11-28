package com.sample.postgresql1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.postgresql1.repository.DbInfoRepository;
import com.sample.postgresql1.service.DbInfoService;
import com.sample.postgresql1.vo.DbInfoVO;

@Service
public class DbInfoServiceImpl implements DbInfoService {
	
	@Autowired
	private DbInfoRepository dbInfoRepository;
	
	public DbInfoVO getDbInfo(){
		
		return dbInfoRepository.getDbInfo();
	}
}
