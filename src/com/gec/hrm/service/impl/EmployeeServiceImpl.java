package com.gec.hrm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gec.hrm.mapper.EmployeeInfMapper;
import com.gec.hrm.pojo.EmployeeInf;
import com.gec.hrm.pojo.EmployeeInfExample;
import com.gec.hrm.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("empService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeInfMapper employeeInfMapper;
	@Override
	public PageInfo<EmployeeInf> findByPage(Integer pageIndex, EmployeeInf emp) {
		EmployeeInfExample ee = new EmployeeInfExample();
		EmployeeInfExample.Criteria cri = ee.createCriteria();
		if(emp.getJobId()!=null && emp.getJobId()>0)
			cri.andJobIdEqualTo(emp.getJobId());
		if(emp.getName()!=null&&!emp.getName().equals(""))
			cri.andNameLike("%"+emp.getName()+"%");
		if (emp.getPhone()!=null&&!emp.getPhone().equals(""))
			cri.andPhoneLike("%"+emp.getPhone()+"%");
		if(emp.getCardId()!=null&&!emp.getCardId().equals(""))
			cri.andCardIdLike("%"+emp.getCardId()+"%");
		if(emp.getSex()!=null)
			cri.andSexEqualTo(emp.getSex());
		if(emp.getDeptId()!=null&&emp.getDeptId()>0)
			cri.andDeptIdEqualTo(emp.getDeptId());
		PageHelper.startPage(pageIndex, 3);
		List<EmployeeInf> list = employeeInfMapper.selectByExample(ee);
		PageInfo<EmployeeInf> info = new PageInfo<>(list);
		return info;
	}

	@Override
	public EmployeeInf findById(int id) {

		return employeeInfMapper.selectByPrimaryKey(id);
	}

	@Override
	public int update(EmployeeInf emp) {

		return employeeInfMapper.updateByPrimaryKey(emp);
	}

	@Override
	public EmployeeInf findByCard(String card) {
		EmployeeInfExample ee = new EmployeeInfExample();
		EmployeeInfExample.Criteria cri = ee.createCriteria();
		cri.andCardIdEqualTo(card);
		List<EmployeeInf> list = employeeInfMapper.selectByExample(ee);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int save(EmployeeInf emp) {
		
		return employeeInfMapper.insert(emp);
	}

	@Override
	public void delete(Integer[] deptIds) {
		for (Integer ids : deptIds) {
			employeeInfMapper.deleteByPrimaryKey(ids);
		}
		
	}

}
