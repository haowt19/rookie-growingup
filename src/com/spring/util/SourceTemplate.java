package com.spring.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * Spring实例管理类
 * 
 * @author shentuwy
 * @date 2012-7-30
 * 
 */
public class SourceTemplate {

	private static ApplicationContext	ac	= null;

	/**
	 * 初始化配置
	 */
	public synchronized static void init() {
		if (ac == null) { 
			ac = new ClassPathXmlApplicationContext(new String[] { "WEB-INF/root-context.xml" });
		}
	}

	/**
	 * 获取Spring配置的bean实例
	 * 
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName) {
		return ac.getBean(beanName);
	}


}
