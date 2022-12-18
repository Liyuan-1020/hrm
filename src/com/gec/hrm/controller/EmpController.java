package com.gec.hrm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gec.hrm.pojo.DeptInf;
import com.gec.hrm.pojo.EmployeeInf;
import com.gec.hrm.pojo.JobInf;
import com.gec.hrm.service.DeptService;
import com.gec.hrm.service.EmployeeService;
import com.gec.hrm.service.JobService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/emp")
public class EmpController {

	@Autowired
	private EmployeeService empService;
	@Autowired
	private JobService jobService;
	@Autowired
	private DeptService deptService;
	
	
	@RequestMapping(value={"/emplist.action","emplist.do"})
	public String findEmployee(EmployeeInf emp,@RequestParam(value="pageIndex",defaultValue="1")Integer pageIndex,Model model){
		PageInfo<EmployeeInf> pageModel = empService.findByPage(pageIndex, emp);
		List<JobInf> jobList = jobService.findAll();
		List<DeptInf> deptList = deptService.findAll();
		model.addAttribute("emp", emp);
		model.addAttribute("pageModel", pageModel);
		model.addAttribute("jobList", jobList);
		model.addAttribute("deptList", deptList);
		return "employee/employeelist";
	}
	
	@RequestMapping("/empaddsave.action")
	public String saveOrUpdate(EmployeeInf emp){
		if(emp.getId()==null){
			empService.save(emp);
		}else{
			empService.update(emp);
		}
		return "redirect:/emp/emplist.action";
	}
	
	@RequestMapping("/empdel.action")
	public String delete(Integer[] empIds){
		empService.delete(empIds);
		return "redirect:/emp/emplist.action";
	}
	
	@RequestMapping("/checkCard.action")
	public void checkName(HttpServletResponse response, String cardId) throws IOException{
		PrintWriter out = response.getWriter();
		if(empService.findByCard(cardId)!=null){
			 out.print("身份证已经被使用!");
		}else
			out.print("身份证可用");
		out.close();
	}
	
	@RequestMapping("/empadd.do")
	public String addjsp(Model model){
		List<JobInf> jobList = jobService.findAll();
		List<DeptInf> deptList = deptService.findAll();
		model.addAttribute("val", "add");
		model.addAttribute("jobList", jobList);
		model.addAttribute("deptList", deptList);
		return "employee/employeeedit";
	}
	@RequestMapping("/empupdate.do")
	public String updatejsp(Model model,Integer id){
		List<JobInf> jobList = jobService.findAll();
		List<DeptInf> deptList = deptService.findAll();
		EmployeeInf emp = empService.findById(id);
		model.addAttribute("employee",emp);
		model.addAttribute("jobList", jobList);
		model.addAttribute("deptList", deptList);
		return "employee/employeeedit";
	}
}
