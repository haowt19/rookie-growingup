package com.retailer.online.panic.dao;

import java.math.BigDecimal;

public interface IFiUserAccountRecordDao {

	public void addAccountRecord(Long uid, BigDecimal money);
	
	
}
