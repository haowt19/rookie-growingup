package com.example.panicbuy.util;

public class Test {
	volatile amend = false;
	if (amend) {
		TimeUnit.MICROSECONDS.sleep(500);
	}
	//抢夺项目剩余可投金额
	BigDecimal resultMoney = getRemainingAmountWithoutLock(money.doubleValue(), key);
	if (resultMoney.compareTo(new BigDecimal(0)) >= 0 ) {
		try {
			//模拟业务处理时间
			TimeUnit.MILLISECONDS.sleep(500);
			resultInfo = "投标成功!";
		} catch (Exception ex) {
			resultInfo= "业务逻辑处理失败，抢标失败";
			returnRemainingAmount(money.doubleValue(), key);
		}
	} else {
		amend = true;
		amendOperation();
		BigDecimal resultMoney = getRemainingAmountWithoutLock(money.doubleValue(), key);
		if (resultMoney.compareTo(new BigDecimal(0)) >= 0 ) {
			try {
				//模拟业务处理时间
				TimeUnit.MILLISECONDS.sleep(500);
				resultInfo = "投标成功!";
			} catch (Exception ex) {
				resultInfo= "业务逻辑处理失败，抢标失败";
				returnRemainingAmount(money.doubleValue(), key);
			}
		} else {
			resultInfo= "项目剩余可投金额不足，投资不成功！";
		}
		
	}
	
}
