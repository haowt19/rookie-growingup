package com.retailer.online.panic.service;

import java.math.BigDecimal;

public interface IPurchaseService {

	
	public void doPuchase(Long prjId, Long uid, BigDecimal money);
	
}
