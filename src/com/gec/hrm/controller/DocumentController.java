package com.gec.hrm.controller;

import com.gec.hrm.pojo.DocumentInf;
import com.gec.hrm.service.DocumentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.UUID;

@Controller
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @RequestMapping("/documentlist.action")
    public String findDocument(DocumentInf document, @RequestParam(value = "pageIndex",defaultValue = "1")Integer pageIndex, Model model){
        PageInfo<DocumentInf>pageModel=documentService.findByPage(pageIndex,document);
        model.addAttribute("document",document);
        model.addAttribute("pageModel",pageModel);
        return "document/documentlist";
    }

    @RequestMapping("/documentadd.do")
    public String documentadd(){
        return "document/documentadd";
    }
  
    /**
     * 上传文件
     * @param documentInf
     * @param file
     * @return
     */
    @RequestMapping("/documentaddsave.action")
    public String uplodFileaddsave(DocumentInf documentInf, @RequestParam(value = "file") MultipartFile file){
        try {
                //获取上下文文件的名称
                String fileName = file.getOriginalFilename();
                System.out.println("filename:" + fileName);
                String uuid = UUID.randomUUID().toString().replace("-", "");
                String Filetype = uuid + fileName.substring(0,fileName.lastIndexOf("."));//后缀
                String Filename = uuid + fileName.substring(fileName.lastIndexOf(".") + 1);//后缀
                System.out.println("Filename:" + Filename);
                System.out.println("Filetype:" + Filetype);
                file.transferTo(new File("D:\\hrm\\upload\\" + Filename + Filetype));
                //把上传文件的路径保存到数据库当中
                documentInf.setFilename("upload/" + Filename);//文件名
                documentInf.setFilename(Filetype);
                System.out.println("上传成功！");
                int b=documentService.save(documentInf);
                if (b>0){
                System.out.println("提交成功!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("提交失败!");
            }
           /* // 判断路径是否存在，如果不存在就创建一个
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }*/
        return "redirect:/document/documentlist.action";
    }

    /**
     * 下载文件
     * @return
     */
    @RequestMapping("/documentdownload.action")
    public String downLoad(){

        return "redirect:/document/documentlist.action";
    }


    @RequestMapping("/updateDocument.action")
    public String  documentaddsave(Integer id,Model model){
        DocumentInf document=documentService.findById(id);
        model.addAttribute("document",document);
        return "document/showUpdateDocument";
    }

    @RequestMapping("/updateloadDocument.action")
    public String documentupdate(DocumentInf documentInf){
        if (documentInf.getId()!=null){
            documentService.update(documentInf);
            System.out.println("修改成功！");
        }
        return "redirect:/document/documentlist.action";
    }

    @RequestMapping("/documentdel.action")
    public String documentdel(Integer[] documentIds){
        documentService.delete(documentIds);
        return "redirect:/document/documentlist.action";
    }
}

