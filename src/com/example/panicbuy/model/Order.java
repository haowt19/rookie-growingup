package com.example.panicbuy.model;

import java.math.BigDecimal;
import java.util.Date;

public class Order {

	/** 订单号 */
	private String orderNo;
	
	/** 订单金额 */
	private BigDecimal money;
	
	/** 用户uid */
	private Long uid;
	
	/** 订单修改时间 */
	private Date mtime;
	
	/** 订单产生时间 */
	private Date ctime;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	
	
}
