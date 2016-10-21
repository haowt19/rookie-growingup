package com.java.spider.example;

import java.util.List;

public class Test {

	
	public void getDataByClass() {
		Rule rule = new Rule(  
                "http://www1.sxcredit.gov.cn/public/infocomquery.do?method=publicIndexQuery",  
        new String[] { "query.enterprisename","query.registationnumber" }, new String[] { "兴网","" },  
                "cont_right", Rule.CLASS, Rule.POST);  
        List<LinkTypeData> extracts = ExtractUtils.extract(rule);  
        printf(extracts); 
	}
	
	@org.junit.Test  
	public void getDatasByCssQueryUserBaidu()  
	{  
	    Rule rule = new Rule("https://buyertrade.taobao.com/trade/itemlist/list_bought_items.htm?t=20110530",  
	            new String[] { "word" }, new String[] { "手表" },  
	            null, -1, Rule.GET);  
	    List<LinkTypeData> extracts = ExtractUtils.extract(rule);  
	    printf(extracts);  
	}  
	
	
	public void printf(List<LinkTypeData> datas)  
    {  
        for (LinkTypeData data : datas)  
        {  
            System.out.println(data.getLinkText());  
            System.out.println(data.getLinkHref());  
            System.out.println("***********************************");  
        }  
  
    }  
}
