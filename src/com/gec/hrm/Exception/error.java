package com.gec.hrm.Exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class error implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        System.out.println("异常已被触发！");
        String error="此页面出现异常，请稍后重试！！！";
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("error",error);
        modelAndView.setViewName("exception");
        return modelAndView;
    }
}
