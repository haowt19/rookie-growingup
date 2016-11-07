package com.business.invest.dao.impl;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;

import com.business.invest.entity.FiPrj;


public class FiPrjDaoImpl implements com.business.invest.dao.IFiPrjDao{
	
	@Resource
	private HibernateTemplate template;

	@Override
	public void savePrj(FiPrj fiPrj) {
		// TODO Auto-generated method stub
		template.save(fiPrj);
	}

	@Override
	public void delPrj(FiPrj fiPrj) {
		// TODO Auto-generated method stub
		template.delete(fiPrj);
	}

	@Override
	public void delPrj(Long prjId) {
		// TODO Auto-generated method stub
		template.delete(getPrj(prjId));
	}

	@Override
	public void updatePrj(FiPrj newPrj) {
		// TODO Auto-generated method stub
		template.update(newPrj);
	}

	@Override
	public FiPrj getPrj(Long prjId) {
		// TODO Auto-generated method stub
		return template.get(FiPrj.class, prjId);
	}

}
