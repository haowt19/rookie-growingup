package com.example.panicbuy.model;

import java.math.BigDecimal;
import java.util.Date;

public class Order {

	/** ������ */
	private String orderNo;
	
	/** ������� */
	private BigDecimal money;
	
	/** �û�uid */
	private Long uid;
	
	/** �����޸�ʱ�� */
	private Date mtime;
	
	/** ��������ʱ�� */
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
