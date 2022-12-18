package com.gec.hrm.service;

import com.gec.hrm.pojo.NoticeInf;
import com.gec.hrm.pojo.TypeInf;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TypeService {
    List<TypeInf> findAll(); //查询所有职位

    PageInfo<TypeInf> findByPage(Integer pageIndex, TypeInf type);

    TypeInf findById(int id);

    int update(TypeInf type);

    int save(TypeInf type);

    void delete(Integer[] typeIds);
}
