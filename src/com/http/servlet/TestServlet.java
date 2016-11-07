package com.http.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.http.MyHttpUtils;

public class TestServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override  
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
//		String jsonFromRequest = MyHttpUtils.receivePost(req, resp);
		System.out.println("========使用doGet获取request中的json对象=====================");
//		System.out.println(jsonFromRequest);
		System.out.println("=============================");
    }
	
	

	@Override  
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		String jsonFromRequest = MyHttpUtils.receivePost(req, resp);
		System.out.println("========使用doPost获取request中的json对象=====================");
		System.out.println(jsonFromRequest);
		System.out.println("=============================");
    }

}
