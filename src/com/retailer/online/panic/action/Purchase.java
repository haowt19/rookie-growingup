package com.retailer.online.panic.action;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.retailer.online.panic.service.IPurchaseService;
import com.retailer.util.RedisUtil;
import com.spring.util.GsonUtil;

public class Purchase {
	
	private IPurchaseService purchaseService;
	
	public void purchase(HttpServletRequest request, HttpServletResponse response) {
		
		preCheck(request);
		
		BigDecimal money = BigDecimal.valueOf(0L);
		
		Long prjId = 1L;
		
		Long uid = 1L;
		
		if (RedisUtil.isRemainingAmountEnough(money)) {
			try {
				purchaseService.doPuchase(prjId, uid, money);
				System.out.println("购买成功!");
			} catch (Exception ex) {
				RedisUtil.returnAmountToCache(money);
				System.out.println("购买失败!!");
			}
		} else {
			System.out.println("剩余额度不足，购买失败!!");
			return;
		}
	}
	

	/**
	 * 合规性校验
	 * @param request
	 */
	private void preCheck(HttpServletRequest request) {
		
	}
	
	
	
}
