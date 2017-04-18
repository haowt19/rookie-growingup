package com.retailer.online.panic.dao;

import java.math.BigDecimal;

public interface IFiPrjDao {

	public void updateRemainingAmount(Long prjId, BigDecimal moeny);
	
}
