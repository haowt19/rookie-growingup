package com.example.panicbuy.util;

public class Test {
	volatile amend = false;
	if (amend) {
		TimeUnit.MICROSECONDS.sleep(500);
	}
	//������Ŀʣ���Ͷ���
	BigDecimal resultMoney = getRemainingAmountWithoutLock(money.doubleValue(), key);
	if (resultMoney.compareTo(new BigDecimal(0)) >= 0 ) {
		try {
			//ģ��ҵ����ʱ��
			TimeUnit.MILLISECONDS.sleep(500);
			resultInfo = "Ͷ��ɹ�!";
		} catch (Exception ex) {
			resultInfo= "ҵ���߼�����ʧ�ܣ�����ʧ��";
			returnRemainingAmount(money.doubleValue(), key);
		}
	} else {
		amend = true;
		amendOperation();
		BigDecimal resultMoney = getRemainingAmountWithoutLock(money.doubleValue(), key);
		if (resultMoney.compareTo(new BigDecimal(0)) >= 0 ) {
			try {
				//ģ��ҵ����ʱ��
				TimeUnit.MILLISECONDS.sleep(500);
				resultInfo = "Ͷ��ɹ�!";
			} catch (Exception ex) {
				resultInfo= "ҵ���߼�����ʧ�ܣ�����ʧ��";
				returnRemainingAmount(money.doubleValue(), key);
			}
		} else {
			resultInfo= "��Ŀʣ���Ͷ���㣬Ͷ�ʲ��ɹ���";
		}
		
	}
	
}
