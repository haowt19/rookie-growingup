package com.retailer.online.panic.dao;

import java.math.BigDecimal;

public interface IFiUserAccountDao {

	public void freezeAccountOrderMoney(Long uid, BigDecimal money);
	
}
