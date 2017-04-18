package com.retailer.online.panic.dao;

import java.math.BigDecimal;

public class FiUserAccountDaoImpl implements IFiUserAccountDao {

	@Override
	public void freezeAccountOrderMoney(Long uid, BigDecimal money) {
		String sql = " update fi_user_account set freeze_money = freeze_money + " + money
				+" , amount = amount - " + money + " where uid = " + uid + " and amount > " + money;

	}

}
