package com.business.invest.dao;

import com.business.invest.entity.FiPrj;

public interface IFiPrjDao {
	
	public void savePrj(FiPrj fiPrj);
	
	
	public void delPrj(FiPrj fiPrj);
	
	
	public void delPrj(Long prjId);
	
	
	public void updatePrj(FiPrj newPrj);
	
	
	public FiPrj getPrj(Long prjId);
	
	
}
