package com.spring.mvc.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.spring.mvc.dao.IRantDao;
import com.spring.mvc.service.IRantService;

@Service
public class RantServiceImpl implements IRantService{

	@Resource
	private IRantDao rantDao;
	
	
	@Override
	public Map<String,Object> getRantInfo() {
		Long prjId = 10L;
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("testdata", Boolean.TRUE);
		return result;
	}

	
}
