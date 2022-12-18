package com.gec.hrm.service.impl;

import com.gec.hrm.mapper.TypeInfMapper;
import com.gec.hrm.pojo.TypeInf;
import com.gec.hrm.pojo.TypeInfExample;
import com.gec.hrm.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeInfMapper typeInfMapper;

    @Override
    public List<TypeInf> findAll() {
        return typeInfMapper.selectByExample(new TypeInfExample());
    }

    @Override
    public PageInfo<TypeInf> findByPage(Integer pageIndex, TypeInf type) {
        TypeInfExample typeInfExample=new TypeInfExample();
        TypeInfExample.Criteria criteria=typeInfExample.createCriteria();
        if (type.getName()!=null&&!type.getName().equals("")) {
            criteria.andNameLike("%"+type.getName()+"%");
        }
        PageHelper.startPage(pageIndex,3);
        List<TypeInf>list=typeInfMapper.selectByExample(typeInfExample);
        PageInfo<TypeInf>info=new PageInfo<>(list);
        return info;
    }

    @Override
    public TypeInf findById(int id) {
        return typeInfMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(TypeInf type) {
        return typeInfMapper.updateByPrimaryKey(type);
    }

    @Override
    public int save(TypeInf type) {
        return typeInfMapper.insert(type);
    }

    @Override
    public void delete(Integer[] typeIds) {
            for (Integer ids:typeIds){
                typeInfMapper.deleteByPrimaryKey(ids);
            }
    }
}
