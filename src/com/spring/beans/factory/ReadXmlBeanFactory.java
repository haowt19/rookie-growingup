package com.spring.beans.factory;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jms.core.JmsTemplate;

public class ReadXmlBeanFactory {

	private static final Logger logger = Logger.getLogger(ReadXmlBeanFactory.class);
	
	@Test
	public void test() {
		try {
			Resource resource = new ClassPathResource("root-context.xml");
			BeanFactory beanFactory = new XmlBeanFactory(resource);
			JmsTemplate jmsTemplate = (JmsTemplate)beanFactory.getBean("jmsTemplate");
			logger.info("利用BeanFactory获取到JMSTemplate的值为"+jmsTemplate);
		} catch (Exception ex) {
			logger.error("利用BeanFactory获取JMSTemplate出错，出错信息为："+ ex);
			try {
				ApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
				JmsTemplate template = (JmsTemplate)context.getBean("jmsTemplate");
				logger.info("利用ApplicationContext获取到JMSTemplate的值为"+template);
			} catch (Exception e) {
				logger.error("利用ApplicationContext获取JMSTemplate出错，出错信息为："+ e);
			}
		}
	}
	
}
