package com.gec.hrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/main.do")
	public String goMain(){
		return "main";
	}
	@RequestMapping("/top.do")
	public String gotop(){
		return "top";
	}
	@RequestMapping("/left.do")
	public String left(){
		return "left";
	}
	@RequestMapping("/right.do")
	public String right(){
		return "right";
	}
}
