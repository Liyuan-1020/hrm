package com.gec.hrm.service.impl;

import com.gec.hrm.mapper.NoticeInfMapper;
import com.gec.hrm.pojo.NoticeInf;
import com.gec.hrm.pojo.NoticeInfExample;
import com.gec.hrm.service.NoticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeInfMapper noticeInfMapper;

    @Override
    public List<NoticeInf> findAll() {
        return noticeInfMapper.selectByExample(new NoticeInfExample());
    }

    @Override
    public PageInfo<NoticeInf> findByPage(Integer pageIndex, NoticeInf noticeInf) {
        NoticeInfExample noticeInfExample=new NoticeInfExample();
        NoticeInfExample.Criteria criteria=noticeInfExample.createCriteria();
        if (noticeInf.getName()!=null&&!noticeInf.getName().equals("")){
            criteria.andNameLike("%"+noticeInf.getName()+"%");
        }
        PageHelper.startPage(pageIndex,3);
        List<NoticeInf>list=noticeInfMapper.selectByExample(noticeInfExample);
        PageInfo<NoticeInf>info=new PageInfo<>(list);
        return info;
    }

    @Override
    public NoticeInf findById(int id) {
        return noticeInfMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(NoticeInf noticeInf) {
        return noticeInfMapper.updateByPrimaryKey(noticeInf);
    }

    @Override
    public int save(NoticeInf noticeInf) {
        return noticeInfMapper.insert(noticeInf);
    }

    @Override
    public void delete(Integer[] noticeIds) {
        for (Integer ids:noticeIds){
            noticeInfMapper.deleteByPrimaryKey(ids);
        }
    }
}
