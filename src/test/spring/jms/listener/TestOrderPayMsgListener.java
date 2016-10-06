package test.spring.jms.listener;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.spring.mvc.service.IQueueService;
import com.spring.util.SourceTemplate;

public class TestOrderPayMsgListener {
	
	
	/**
	 * 测试jms监听程序
	 */
	@Test
	public void testListener() {
		String path="WebRoot/WEB-INF/root-context.xml"; 
		ApplicationContext context = new FileSystemXmlApplicationContext(path);
		IQueueService queueService = (IQueueService) context.getBean("queueServiceImpl");
		
		String queId = queueService.getQueId();
		String destName = "order_pay_queue";
		String destType = "doOrderPay";
		final Object msgObj = "{\"apiId\":\"1\",\"isCallBack\":\"1\",\"tenantId\":30915}";
		String objType = "prjPAy";
		String objId = "14915";
		
		queueService.msgInsert(queId, destName, destType, msgObj, objType, objId);
	}

}
