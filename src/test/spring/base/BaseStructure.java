package test.spring.base;

import java.sql.Array;

import org.apache.log4j.Logger;
import org.apache.log4j.lf5.LogLevel;

import com.spring.mvc.service.IQueueService;

public class BaseStructure {
	
	private static final Logger logger = Logger.getLogger(BaseStructure.class);
	
	private IQueueService queueService;
	
	
	
	public Object methodInvoke(Object param) {
		try {
			queueService.getClass();
			if (logger.getLevel().equals(LogLevel.DEBUG)) {
				logger.debug("操作执行成功");
			}
		} catch (Exception ex) {
			
		}
		
		return param;
	}
	
	

}
