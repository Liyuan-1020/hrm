package com.gec.hrm.service.impl;

import com.gec.hrm.mapper.DocumentInfMapper;
import com.gec.hrm.pojo.DocumentInf;
import com.gec.hrm.pojo.DocumentInfExample;
import com.gec.hrm.service.DocumentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentInfMapper documentInfMapper;

    @Override
    public List<DocumentInf> findAll() {
        return documentInfMapper.selectByExample(new DocumentInfExample());
    }

    @Override
    public PageInfo<DocumentInf> findByPage(Integer pageIndex, DocumentInf documentInf) {
        DocumentInfExample documentInfExample=new DocumentInfExample();
        DocumentInfExample.Criteria criteria=documentInfExample.createCriteria();
        if (documentInf.getTitle()!=null&&!documentInf.getTitle().equals("")){
            criteria.andTitleLike("%"+documentInf.getTitle()+"%");
        }
        PageHelper.startPage(pageIndex,3);
        List<DocumentInf>list=documentInfMapper.selectByExample(documentInfExample);
        PageInfo<DocumentInf>pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public DocumentInf findById(int id) {
        return documentInfMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(DocumentInf documentInf) {
        return documentInfMapper.updateByPrimaryKey(documentInf);
    }

    @Override
    public int save(DocumentInf documentInf) {
        try {
            System.out.println(documentInf.getFilename());
            System.out.println(documentInf.getFiletype());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return documentInfMapper.insert(documentInf);
    }

    @Override
    public void delete(Integer[] documentIds) {
        for (Integer Ids:documentIds){
            documentInfMapper.deleteByPrimaryKey(Ids);
        }
    }
}
