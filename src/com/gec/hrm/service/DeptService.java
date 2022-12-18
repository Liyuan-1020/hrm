package com.gec.hrm.service;

import java.util.List;

import com.gec.hrm.pojo.DeptInf;
import com.github.pagehelper.PageInfo;

public interface DeptService {

	List<DeptInf> findAll(); //查询所有职位
	
	PageInfo<DeptInf> findByPage(Integer pageIndex, DeptInf dept);

	DeptInf findById(int id);

	int update(DeptInf dept);

	int save(DeptInf dept);

	void delete(Integer[] deptIds);
}
