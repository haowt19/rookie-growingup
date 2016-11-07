package com.spring.mvc.controller;

import java.lang.reflect.Proxy;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;

import com.spring.mvc.service.IRantService;

/**
 * 
 * @author 001244
 *
 */
public class HomePageController implements Controller{
	
	@Resource
	private IRantService rantService;
	
	private MethodNameResolver methodNameResolver;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println(rantService.getRantInfo());
		
		return new ModelAndView("home");
	}
	
	
	public ModelAndView testMethodResolver(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("测试methodResolver方法");
		
		return new ModelAndView("home");
		
	}


	public MethodNameResolver getMethodNameResolver() {
		return methodNameResolver;
	}


	public void setMethodNameResolver(MethodNameResolver methodNameResolver) {
		this.methodNameResolver = methodNameResolver;
	}
	

	
	
}
