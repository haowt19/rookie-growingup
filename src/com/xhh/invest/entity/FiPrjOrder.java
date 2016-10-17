package com.xhh.invest.entity;

import java.math.BigDecimal;
import java.util.Date;

public class FiPrjOrder {
	
	private Long id;
	
	/** 投资者uid */
	private Long uid;
	
	/** 投资金额 */
	private BigDecimal money;
	
	/** 投资时间 */
	private Date investTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Date getInvestTime() {
		return investTime;
	}

	public void setInvestTime(Date investTime) {
		this.investTime = investTime;
	}
	
}
