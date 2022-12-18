package com.gec.hrm.service;
import com.gec.hrm.pojo.DocumentInf;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DocumentService {
    List<DocumentInf> findAll(); //查询所有职位

    PageInfo<DocumentInf> findByPage(Integer pageIndex, DocumentInf documentInf);

    DocumentInf findById(int id);

    int update(DocumentInf documentInf);

    int save(DocumentInf documentInf);

    void delete(Integer[] documentIds);
}
