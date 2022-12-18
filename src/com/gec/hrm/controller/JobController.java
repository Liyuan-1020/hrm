package com.gec.hrm.controller;

import com.gec.hrm.pojo.JobInf;
import com.gec.hrm.service.JobService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @RequestMapping("/joblist.action")
    public String findJob(JobInf job, @RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex, Model model){
        PageInfo<JobInf>pageModel=jobService.findByPage(pageIndex,job);
        model.addAttribute("job",job);
        model.addAttribute("pageModel", pageModel);
        return "job/joblist";
    }

    @RequestMapping("/jobadd.do")
    public String addJob(Model model){
        model.addAttribute("val","add");
        return "job/jobedit";
    }

    @RequestMapping("/addOrUpdate.action")
    public String addOrUpdate(JobInf job){
        if (job.getId()==null){
            jobService.save(job);
        }else {
            jobService.update(job);
        }
        return "redirect:/job/joblist.action";
    }

    @RequestMapping("/updateJob.action")
    public String updateJob(Model model,Integer id){
        JobInf jobInf=jobService.findById(id);
        model.addAttribute("job",jobInf);
        return "job/jobedit";
    }

    @RequestMapping("/jobdel.action")
    public String jobdel(Integer[] jobIds){
        jobService.delete(jobIds);
        return "redirect:/job/joblist.action";
    }
}
