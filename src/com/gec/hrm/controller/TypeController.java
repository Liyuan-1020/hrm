package com.gec.hrm.controller;

import com.gec.hrm.pojo.TypeInf;
import com.gec.hrm.service.TypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @RequestMapping("/typelist.action")
     public String findType(TypeInf typeInf, @RequestParam(value = "pageIndex",defaultValue = "1")Integer pageIndex, Model model){
        PageInfo<TypeInf>pageModel=typeService.findByPage(pageIndex,typeInf);
        model.addAttribute("type",typeInf);
        model.addAttribute("pageModel",pageModel);
        return "notice/typelist";
    }

    @RequestMapping("/typeadd.do")
    public String toaddType(){
        return "notice/type_save_update";
    }
    @RequestMapping("/updateType")
    public String updateType(Model model,Integer id){
        TypeInf typeInf=typeService.findById(id);
        model.addAttribute("type",typeInf);
        return "notice/type_save_update";
    }

    @RequestMapping("/typeSaveOrUpdate.action")
    public String typeSaveOrUpdate(TypeInf typeInf){
        if (typeInf.getId()==null){
            typeService.save(typeInf);
        }else{
            typeService.update(typeInf);
        }
        return "redirect:/type/typelist.action";
    }

    @RequestMapping("/typedel.action")
    public String typedel(Integer [] typeIds){
        typeService.delete(typeIds);
        return "redirect:/type/typelist.action";
    }
}
