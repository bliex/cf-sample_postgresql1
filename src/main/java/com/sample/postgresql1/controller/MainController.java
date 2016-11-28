package com.sample.postgresql1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sample.postgresql1.service.DbInfoService;
import com.sample.postgresql1.vo.DbInfoVO;

@Controller
public class MainController {
	
	@Autowired
	private DbInfoService dbInfoService;
	
	@RequestMapping(value="/")
	public ModelAndView helloWorld() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		
		DbInfoVO dbVo = dbInfoService.getDbInfo();
		mv.addObject("connectionId", dbVo.getConnectionId());
		mv.addObject("variables", dbVo.getVariables());
		
		return mv;
	}
}
