package com.gec.hrm.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gec.hrm.pojo.UserInf;
import com.gec.hrm.service.UserService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/loginForm.do")
	public String goLoginForm(){
		return "loginForm";
	}
	
	@RequestMapping("/login.action")
	public String login(HttpSession session,Model model,UserInf user){
		UserInf use = userService.login(user.getLoginname(), user.getPassword());
		if(use!=null){
			session.setAttribute("user_session", use);
			return "redirect:/main.do";
		}else{
			model.addAttribute("message", "用户名或密码错误!");
			return "loginForm";
		}
	}

	//注销退出
	@RequestMapping("/logout.action")
	public String logout(HttpSession session){
		session.removeAttribute("user_session");
		return "loginForm";
	}
	
	@RequestMapping("/userlist.action")
	public String findUser(UserInf user,@RequestParam(value="pageIndex",defaultValue="1")Integer pageIndex,Model model){
		PageInfo<UserInf> pageModel = userService.findByPage(pageIndex, user);
		model.addAttribute("user", user);
		model.addAttribute("pageModel", pageModel);
		return "user/userlist";
	}
	
	@RequestMapping("/useraddsave.action")
	public String saveOrUpdate(UserInf user){
		if(user.getId()==null){
			userService.save(user);
		}else{
			userService.update(user);
		}
		return "redirect:/user/userlist.action";
	}
	
	@RequestMapping("/userdel.action")
	public String delete(HttpSession session,Integer[] userIds){
		Integer id = ((UserInf)session.getAttribute("user_session")).getId();
		userService.delete(userIds,id);
		return "redirect:/user/userlist.action";
	}
	
	@RequestMapping("/checkName.action")
	public void checkName(HttpServletResponse response, String loginname) throws IOException{
		PrintWriter out = response.getWriter();
		if(userService.findByName(loginname)!=null){
			System.out.println("用户已存在");
			 out.print("用户名已经被使用!");
		}else
			out.print("用户名可用");
		out.close();
	}
	
	@RequestMapping("/useradd.do")
	public String addjsp(Model model){
		model.addAttribute("val", "add");
		return "user/useradd";
	}
	@RequestMapping("/userupdate.do")
	public String updatejsp(Model model,Integer id){
		UserInf user = userService.findById(id);
		model.addAttribute("user",user);
		return "user/useradd";
	}
}
