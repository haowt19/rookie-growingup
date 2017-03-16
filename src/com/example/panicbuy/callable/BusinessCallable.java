package com.example.panicbuy.callable;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import com.example.panicbuy.model.Order;
import com.xhh.framework.spring.SourceTemplate;

public class BusinessCallable implements Callable<Boolean>{

	private Long uid;
	
	private BigDecimal money;
	
	private IOrderService orderService;
	
	public BusinessCallable(Long uid, BigDecimal money) {
		super();
		this.uid = uid;
		this.money = money;
		
		orderService = SourceTemplate.getBean(OrderService.class);
	}
	
	@Override
	public Boolean call() throws Exception {
		//生成订单
		Order order = getOrder(this.uid, this.money);
		orderService.save(order);
		
		//冻结账户余额
		
		
		//生成冻结流水
		
		
		//发送投资成功短信
		
		return null;
	}


	
	
	private Order getOrder(Long uid, BigDecimal money) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		String orderNo = uid + sdf.format(new Date());
		
		Order order =  new Order();
		order.setUid(uid);
		order.setMoney(money);
		order.setOrderNo(orderNo);
		order.setMtime(new Date());
		order.setCtime(new Date());
		
		return order;
	}
}
