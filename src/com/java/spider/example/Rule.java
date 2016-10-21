package com.java.spider.example;

public class Rule {

	/** 连接url */
	private String url;
	
	/** 参数集合 */
	private String[] params;
	
	/** 参数对应的值*/
	private String[] values;
	
	/** 对返回的HTML，第一次过滤所用的标签 */
	private String resultTypeName;
	
	/** CLASS、ID、SELECTION */
	private int type = ID;
	
	/** 请求方法 */
	private int requestMethod = GET;
	
	public final static int GET = 0;
	
	public final static int POST = 1;
	
	public final static int CLASS = 0;
	
	public final static int ID = 1;
	
	public final static int SELECTION = 2;
	
	
	public Rule() {}


	public Rule(String url, String[] params, String[] values,
			String resultTypeName, int type, int requestMethod) {
		super();
		this.url = url;
		this.params = params;
		this.values = values;
		this.resultTypeName = resultTypeName;
		this.type = type;
		this.requestMethod = requestMethod;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String[] getParams() {
		return params;
	}


	public void setParams(String[] params) {
		this.params = params;
	}


	public String[] getValues() {
		return values;
	}


	public void setValues(String[] values) {
		this.values = values;
	}


	public String getResultTypeName() {
		return resultTypeName;
	}


	public void setResultTypeName(String resultTypeName) {
		this.resultTypeName = resultTypeName;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public int getRequestMethod() {
		return requestMethod;
	}


	public void setRequestMethod(int requestMethod) {
		this.requestMethod = requestMethod;
	}
	
}
