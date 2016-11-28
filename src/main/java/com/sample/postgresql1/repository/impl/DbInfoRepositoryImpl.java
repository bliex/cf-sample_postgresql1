package com.sample.postgresql1.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sample.postgresql1.repository.DbInfoRepository;
import com.sample.postgresql1.vo.DbInfoVO;

@Repository
public class DbInfoRepositoryImpl implements DbInfoRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public DbInfoVO getDbInfo(){
		DbInfoVO dbVo = new DbInfoVO();
		
//		dbVo.setConnectionId( jdbcTemplate.queryForObject("SELECT CONNECTION_ID()", String.class) );
		
		dbVo.setVariables( jdbcTemplate.queryForList("SHOW ALL") );
		
		return dbVo;
	}
}
