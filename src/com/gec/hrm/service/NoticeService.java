package com.gec.hrm.service;
import com.gec.hrm.pojo.NoticeInf;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface NoticeService {

    List<NoticeInf> findAll(); //查询所有职位

    PageInfo<NoticeInf> findByPage(Integer pageIndex, NoticeInf noticeInf);

    NoticeInf findById(int id);

    int update(NoticeInf notice);

    int save(NoticeInf notice);

    void delete(Integer[] noticeIds);
}
