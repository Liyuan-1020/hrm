package com.gec.hrm.controller;

import com.gec.hrm.pojo.DeptInf;
import com.gec.hrm.service.DeptService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping("/deptlist.action")
    public String findDept(DeptInf dept, @RequestParam(value = "pageIndex",defaultValue = "1")Integer pageIndex, Model model){
        PageInfo<DeptInf>pageModel=deptService.findByPage(pageIndex,dept);
        model.addAttribute("pageModel",pageModel);
        model.addAttribute("dept",dept);
        return "dept/deptlist";
    }


    @RequestMapping("/saveOrUpdate.action")
    public String saveOrUpdate(DeptInf deptInf){
        if (deptInf.getId()==null){
            deptService.save(deptInf);
        }else {
            deptService.update(deptInf);
        }
        return "redirect:/dept/deptlist.action";
    }

    @RequestMapping("/deptadd.do")
    public String addDept(Model model){
        model.addAttribute("val","add");
        return "dept/deptedit";
    }

    @RequestMapping("/updateDept.action")
    public String updateDept(Model model,Integer id){
        DeptInf dept=deptService.findById(id);
        model.addAttribute("dept",dept);
        return "dept/deptedit";
    }

    @RequestMapping("/deptdel.action")
    public String deptdel(Integer[] deptIds){
        deptService.delete(deptIds);
        return "redirect:/dept/deptlist.action";
    }
}
