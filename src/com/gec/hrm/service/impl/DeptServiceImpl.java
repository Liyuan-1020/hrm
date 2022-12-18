package com.gec.hrm.service.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gec.hrm.mapper.DeptInfMapper;
import com.gec.hrm.pojo.DeptInf;
import com.gec.hrm.pojo.DeptInfExample;
import com.gec.hrm.service.DeptService;
import com.github.pagehelper.PageInfo;

@Service("deptService")
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptInfMapper deptInfMapper;
	@Override
	public List<DeptInf> findAll() {
		return deptInfMapper.selectByExample(new DeptInfExample());
	}

	@Override
	public PageInfo<DeptInf> findByPage(Integer pageIndex, DeptInf dept) {
		DeptInfExample deptInfExample=new DeptInfExample();
		DeptInfExample.Criteria criteria=deptInfExample.createCriteria();
		if (dept.getName()!=null&&!dept.getName().equals("")) {
			criteria.andNameLike("%" + dept.getName() + "%");
		}
		PageHelper.startPage(pageIndex,3);
		List<DeptInf>list=deptInfMapper.selectByExample(deptInfExample);
		PageInfo<DeptInf>info=new PageInfo<>(list);
		return info;
	}

	@Override
	public DeptInf findById(int id) {

		return deptInfMapper.selectByPrimaryKey(id);
	}

	@Override
	public int update(DeptInf dept) {

		return deptInfMapper.updateByPrimaryKey(dept);
	}

	@Override
	public int save(DeptInf dept) {

		return deptInfMapper.insert(dept);
	}

	@Override
	public void delete(Integer[] deptIds) {
		for (Integer ids:deptIds){
			deptInfMapper.deleteByPrimaryKey(ids);
		}
	}

}
