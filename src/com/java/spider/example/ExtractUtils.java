package com.java.spider.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ExtractUtils {

	/**
	 * 根据一定的规则来抽取网页数据
	 * @param rule
	 * @return
	 */
	public static List<LinkTypeData> extract(Rule rule) {
		
		List<LinkTypeData> datas = new ArrayList<LinkTypeData>();
		
		LinkTypeData data = null;
		
		String url = rule.getUrl();  
        String[] params = rule.getParams();  
        String[] values = rule.getValues();  
        String resultTagName = rule.getResultTypeName();  
        int type = rule.getType();  
        int requestType = rule.getRequestMethod();  
        
        Connection conn = Jsoup.connect(url);  
		
        if (params != null) {
        	for (int i=0;i<params.length;i++) {
        		conn.data(params[i], values[i]);
        	}
        }
        
        Document doc = null;
        switch (requestType) {
        case Rule.GET:
        	try {
				doc = conn.timeout(100000).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	break;
        case Rule.POST:
        	try {
				doc = conn.timeout(100000).post();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	break;
        }
        
        Elements results = new Elements();  
        switch (type)  
        {  
        case Rule.CLASS:  
            results = doc.getElementsByClass(resultTagName);  
            break;  
        case Rule.ID:  
            Element result = doc.getElementById(resultTagName);  
            results.add(result);  
            break;  
        case Rule.SELECTION:  
            results = doc.select(resultTagName);  
            break;  
        default:  
            //当resultTagName为空时默认去body标签  
            if (StringUtil.isBlank(resultTagName))  
            {  
                results = doc.getElementsByTag("body");  
            }  
        }  
        
        for (Element result : results)  
        {  
            Elements links = result.getElementsByTag("a");  

            for (Element link : links)  
            {  
                //必要的筛选  
                String linkHref = link.attr("href");  
                String linkText = link.text();  

                data = new LinkTypeData();  
                data.setLinkHref(linkHref);  
                data.setLinkText(linkText);  

                datas.add(data);  
            }  
        }  
        
		return datas;
	}
	
	
	
}
