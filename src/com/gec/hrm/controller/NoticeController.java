package com.gec.hrm.controller;

import com.gec.hrm.pojo.NoticeInf;
import com.gec.hrm.pojo.TypeInf;
import com.gec.hrm.service.NoticeService;
import com.gec.hrm.service.TypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;
    @Autowired
    private TypeService typeService;

    @RequestMapping("/noticelist.action")
    public String findNotice(NoticeInf noticeInf, @RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex, Model model){
        PageInfo<NoticeInf>pageModel=noticeService.findByPage(pageIndex,noticeInf);
        model.addAttribute("notice",noticeInf);
        model.addAttribute("pageModel",pageModel);
        return "notice/noticelist";
    }

    @RequestMapping("/noticeadd.do")
    public String toaddNotice(Model model){
        List<TypeInf>type=typeService.findAll();
        model.addAttribute("val","add");
        model.addAttribute("type",type);
        return "notice/notice_save_update";
    }

    @RequestMapping("/noticeSaveOrUpdate.action")
    public String noticeSaveOrUpdate(NoticeInf noticeInf){
        if (noticeInf.getId()==null){
            noticeService.save(noticeInf);
        }else {
            noticeService.update(noticeInf);
        }
        return "redirect:/notice/noticelist.action";
    }

    @RequestMapping("/updateNotice.action")
    public String updateNotice(Model model,Integer id){
        NoticeInf notice=noticeService.findById(id);
        List<TypeInf>type=typeService.findAll();
        model.addAttribute("type",type);
        model.addAttribute("notice", notice);
        return "notice/notice_save_update";
    }

    @RequestMapping("/noticedel.action")
    public String deleteNotice(Integer[] noticeIds){
        noticeService.delete(noticeIds);
        return "notice/noticelist";
    }
}
