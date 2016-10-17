package com.xhh.invest.entity;

import java.math.BigDecimal;

public class FiPrj {

	private Long id;
	
	private String prjName;
	
	/** 募集金额 */
	private BigDecimal demandAmount;
	
	/** 剩余募集金额 */
	private BigDecimal	remainingAmount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrjName() {
		return prjName;
	}

	public void setPrjName(String prjName) {
		this.prjName = prjName;
	}

	public BigDecimal getDemandAmount() {
		return demandAmount;
	}

	public void setDemandAmount(BigDecimal demandAmount) {
		this.demandAmount = demandAmount;
	}

	public BigDecimal getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(BigDecimal remainingAmount) {
		this.remainingAmount = remainingAmount;
	}
	
	
	
}
