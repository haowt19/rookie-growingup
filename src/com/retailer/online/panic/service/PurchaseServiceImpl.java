package com.retailer.online.panic.service;

import java.math.BigDecimal;

import com.retailer.online.panic.dao.IFiPrjDao;
import com.retailer.online.panic.dao.IFiPrjOrderDao;
import com.retailer.online.panic.dao.IFiUserAccountDao;
import com.retailer.online.panic.dao.IFiUserAccountRecordDao;

public class PurchaseServiceImpl implements IPurchaseService {
	
	private IFiPrjDao fiPrjDao;
	
	private IFiPrjOrderDao fiPrjOrderDao;
	
	private IFiUserAccountDao fiUserAccountDao;
	
	private IFiUserAccountRecordDao fiUserAccountRecordDao;
	
	

	@Override
	public void doPuchase(Long prjId, Long uid, BigDecimal money) {
		fiPrjDao.updateRemainingAmount(prjId, money);
		
		fiUserAccountDao.freezeAccountOrderMoney(uid, money);
		
		fiUserAccountRecordDao.addAccountRecord(uid, money);
		
		fiPrjOrderDao.addPrjOrder(prjId, uid, money);
	}

}
