package com.gec.hrm.service;

import com.gec.hrm.pojo.EmployeeInf;
import com.github.pagehelper.PageInfo;

public interface EmployeeService{
	
	PageInfo<EmployeeInf> findByPage(Integer pageIndex, EmployeeInf emp);

	EmployeeInf findById(int id);

	int update(EmployeeInf emp);
	
	EmployeeInf findByCard(String card);

	int save(EmployeeInf emp);

	void delete(Integer[] deptIds);
}
