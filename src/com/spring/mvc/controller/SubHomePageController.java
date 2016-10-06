package com.spring.mvc.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mvc.service.IRantService;

/**
 * 使用注解来实现spring mvc的请求过程
 * @author 001244
 *
 */


@Controller
@RequestMapping("/sub")
public class SubHomePageController {
	
	private static final Logger logger = Logger.getLogger(SubHomePageController.class);
	
	@Resource
	private IRantService rantService;
	
	@RequestMapping("/subhome")
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("the request has enter action SubHomePageController");
		
		System.out.println(rantService.getRantInfo());
		
		return new ModelAndView("subpage");
	}
	
	@RequestMapping("/subhometest")
	protected ModelAndView handleRequestInternal() {
		
		Map<String,Object> rantMap = rantService.getRantInfo();
		
		return new ModelAndView("subpage", rantMap);
	}
	
}
