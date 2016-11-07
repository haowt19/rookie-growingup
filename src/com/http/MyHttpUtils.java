package com.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.spring.util.SourceTemplate;

public class MyHttpUtils {


	/*=========================用httpPost对Map<String, String>发送和接收的例子===============================*/
	/**超时设置为10s*/
	private static RequestConfig defaultRequestConfig = 
			RequestConfig.custom().
			setSocketTimeout(10000).
			setConnectTimeout(10000).
			setConnectionRequestTimeout(10000).
			setStaleConnectionCheckEnabled(true).
			build();
	
	/**
	 * 
	 * @param uri
	 * @param params
	 * @return
	 */
	public static final String postSupportRedirect(String uri, Map<String, String> params) {
		String result = null;
		
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		httpClientBuilder.setRedirectStrategy(new LaxRedirectStrategy());
		CloseableHttpClient httpClient = httpClientBuilder.build();
		
		CloseableHttpResponse response = null;
		
		try {
			HttpPost request = new HttpPost(uri);
			request.setConfig(defaultRequestConfig);
			
			//追加请求头部信息
			Map<String, String> reqHeadMap = new HashMap<String, String>();
			reqHeadMap.put("key1", "value1");
			reqHeadMap.put("key2", "value2");
			
			if (params != null && params.size() > 0) {
				List<NameValuePair> nvp = new ArrayList<NameValuePair>();
				Iterator<Map.Entry<String,String>> it = params.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<String, String> entry = it.next();
					nvp.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				request.setEntity(new UrlEncodedFormEntity(nvp, "UTF-8"));
			}
			
			//获取加密串
			String secretString = "123234";
			String encrptMsg = encrptMsg(params, secretString);
			
			reqHeadMap.put("key3", encrptMsg);
			for (Iterator<Map.Entry<String, String>> it = reqHeadMap.entrySet().iterator(); it.hasNext();) {
				Map.Entry<String, String> entry = it.next();
				request.setHeader(entry.getKey(), entry.getValue());
			}
			
			response = httpClient.execute(request);
			
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 404) {
	            throw new RuntimeException("请求返回状态码【" +  statusCode + "】异常");
	        }
			
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, "UTF-8");
			EntityUtils.consume(entity);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (response != null) {
                try {
                    response.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
		}
		
		return result;
	}
	
	public static final String encrptMsg(Map<String, String> params, String encryptKey) {
		String result = null;
        StringBuilder sb = new StringBuilder();
        if (params != null && params.size() > 0) {
            List<String> nameList = new ArrayList<String>();
            for (Iterator<Map.Entry<String, String>> it = params.entrySet().iterator(); it.hasNext();) {
                Map.Entry<String, String> entry = it.next();
                nameList.add(entry.getKey());
            }

            Collections.sort(nameList);

            for (String name : nameList) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(name).append("=").append(params.get(name));
            }

        }
        sb.append(encryptKey);
        result = DigestUtils.md5Hex(sb.toString());
        return result;
	}
	
	
	/*=========================用httpPost对JSON发送和接收的例子===============================*/
	private static final String APPLICATION_JSON = "application/json";
    
    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";
	
	
	@SuppressWarnings({ "deprecation", "resource" })
	public static void httpPostWithJson(String url, String json) throws Exception {
		String encodeJson = URLEncoder.encode(json, HTTP.UTF_8);
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
		
		StringEntity stringEntity = new StringEntity(encodeJson);
		stringEntity.setContentType(CONTENT_TYPE_TEXT_JSON);
		stringEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
		httpPost.setEntity(stringEntity);
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient.execute(httpPost);
	}
	
	
	@SuppressWarnings("deprecation")
	public static String receivePost(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, UnsupportedEncodingException {
        
        // 读取请求内容
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }

        // 将资料解码
        String reqBody = sb.toString();
        
        response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		String message = URLDecoder.decode(reqBody, HTTP.UTF_8);
		writer.write(message);
		writer.flush();
		writer.close();
        return message;
    }
	
	/*=======================上传文件=================================*/
	public static String upload(String requestUrl, File file) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = "";
		try {
			HttpPost httpPost = new HttpPost(requestUrl);
			FileBody fileBody = new FileBody(file);
			StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);  
			
			HttpEntity httpEntity = MultipartEntityBuilder.create().addPart("fileBody",fileBody).addPart("comment", comment).build();
			httpPost.setEntity(httpEntity);
			
			System.out.println("executing request " + httpPost.getRequestLine());  
			
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			try {
				StatusLine statusLine = httpResponse.getStatusLine();
				int statusCode = statusLine.getStatusCode();
				System.out.println("请求返回的状态码为：" + statusCode);
				HttpEntity responseEntity = httpResponse.getEntity();
				result = EntityUtils.toString(responseEntity, "UTF-8");
				
				EntityUtils.consume(responseEntity);  
			} finally {
				httpResponse.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	
}
