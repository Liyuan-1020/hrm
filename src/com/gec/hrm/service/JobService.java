package com.gec.hrm.service;

import java.util.List;

import com.gec.hrm.pojo.JobInf;
import com.github.pagehelper.PageInfo;


public interface JobService{

	List<JobInf> findAll(); //查询所有职位
	
	PageInfo<JobInf> findByPage(Integer pageIndex, JobInf jobInf);

	JobInf findById(int id);

	int update(JobInf job);

	int save(JobInf job);

	void delete(Integer[] jobIds);
}
