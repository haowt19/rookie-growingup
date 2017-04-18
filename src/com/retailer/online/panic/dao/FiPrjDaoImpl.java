package com.retailer.online.panic.dao;

import java.math.BigDecimal;

public class FiPrjDaoImpl implements IFiPrjDao {

	@Override
	public void updateRemainingAmount(Long prjId, BigDecimal money) {
		String hql = " update fi_prj set remaining_amount = remaining_amount -  " + money
				+ "where prj_id = " + prjId + " and remaining_amount > money";

	}

}
