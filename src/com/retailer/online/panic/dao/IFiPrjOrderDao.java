package com.retailer.online.panic.dao;

import java.math.BigDecimal;

public interface IFiPrjOrderDao {

	public void addPrjOrder(Long prjId, Long uid, BigDecimal money);
	
}
