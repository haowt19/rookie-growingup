package com.http;

import org.junit.Test;

public class TestHttpCommunication {

	@Test
	public void httpCommunication() {
		String url = "http://localhost:8080/spring-learning-hwt/test.htm";
		String result = "{"
				+ "\"sucsum\":\"500500\","
				+ "\"projectStatus\":\"1\","
				+ "\"failcount\":\"0\","
				+ "\"secretKey\":\"6d8fd6c6894470dc74783c10c3d77a7c\","
				+ "\"details\":["
				+ "		{\"fee\":\"0\","
				+ "		\"amount\":\"498500\","
				+ "		\"status\":\"4\","
				+ "		\"settleDate\":\"20161028122339\","
				+ "		\"bankSerialNo\":\"PT1610280020351\","
				+ "		\"p2pSerialNo\":\"XHHZFDD201610280000003\"},"
				+ "		{\"fee\":\"0\","
				+ "		\"amount\":\"2000\","
				+ "		\"status\":\"4\","
				+ "		\"settleDate\":\"20161028122305\","
				+ "		\"bankSerialNo\":\"PT1610280020352\","
				+ "		\"p2pSerialNo\":\"XHHPOBS201610280000008\"}"
				+ "],"
				+"\"succount\":\"2\","
				+"\"failsum\":\"0\","
				+ "\"projectId\":\"XHH15995\"}";
		
		try {
			MyHttpUtils.httpPostWithJson(url, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
