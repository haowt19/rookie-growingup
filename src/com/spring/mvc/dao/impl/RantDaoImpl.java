package com.spring.mvc.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.spring.mvc.dao.IRantDao;

@Repository
public class RantDaoImpl implements IRantDao{

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	
	
	@Override
	public Map<String,Object> getPrjInfo(Long prjId) {
		String sql = "select * from fi_prj where id = "+prjId;
		
		return jdbcTemplate.queryForMap(sql);
	}

}
