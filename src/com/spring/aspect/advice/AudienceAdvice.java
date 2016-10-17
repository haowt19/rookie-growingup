package com.spring.aspect.advice;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

import com.spring.aspect.Audience;

public class AudienceAdvice  implements
		MethodBeforeAdvice,
		AfterReturningAdvice,
		MethodInterceptor,
		ThrowsAdvice {
	
	private static final Logger logger = Logger.getLogger(Audience.class);
	
	private Audience audience;
	
	
	

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] arg2,
			Object target) throws Throwable {
		logger.info("正在执行切面中afterReturning方法，参数为四个，第一个returnValue="+returnValue
				+"第二个method="+method+"第三个arg2="+arg2+"第四个target="+target);
		audience.applaud();
	}

	@Override
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		logger.info("正在执行切面中before方法，参数为三个，第二个method="
			+method+"第三个arg2="+args+"第四个target="+target);
		audience.takeSeats();
		audience.turnOffCellPhones();
	}
	
	
	public void afterThrowing(Throwable throwable) {
		logger.info("正在执行切面中afterThrowing方法，参数throwable="+throwable);
		audience.demandRefund();
	}
	

	public void setAudience(Audience audience) {
		this.audience = audience;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		try {
			audience.takeSeats();
			audience.turnOffCellPhones();
			
			Object returnValue = invocation.proceed();
			audience.applaud();
			return returnValue;
		} catch (Throwable ex) {
			audience.demandRefund();
			throw ex;
		}
		
	}
	
}
