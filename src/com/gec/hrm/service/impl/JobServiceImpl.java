package com.gec.hrm.service.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gec.hrm.mapper.JobInfMapper;
import com.gec.hrm.pojo.JobInf;
import com.gec.hrm.pojo.JobInfExample;
import com.gec.hrm.service.JobService;
import com.github.pagehelper.PageInfo;

@Service("jobService")
public class JobServiceImpl implements JobService {

	@Autowired
	private JobInfMapper jobInfMapper;
	@Override
	public List<JobInf> findAll() {

		return jobInfMapper.selectByExample(new JobInfExample());
	}

	@Override
	public PageInfo<JobInf> findByPage(Integer pageIndex, JobInf jobInf) {
		JobInfExample jobInfExample=new JobInfExample();
		JobInfExample.Criteria criteria=jobInfExample.createCriteria();
		if (jobInf.getName()!=null&&!jobInf.getName().equals("")){
			criteria.andNameLike("%"+jobInf.getName()+"%");
		}
		PageHelper.startPage(pageIndex,3);
		List<JobInf>list=jobInfMapper.selectByExample(jobInfExample);
		PageInfo<JobInf>info=new PageInfo<>(list);
		return info;
	}

	@Override
	public JobInf findById(int id) {

		return jobInfMapper.selectByPrimaryKey(id);
	}

	@Override
	public int update(JobInf job) {
		return jobInfMapper.updateByPrimaryKey(job);
	}

	@Override
	public int save(JobInf job) {
		return jobInfMapper.insert(job);
	}

	@Override
	public void delete(Integer[] jobIds) {
		for (Integer ids:jobIds){
			jobInfMapper.deleteByPrimaryKey(ids);
		}
	}


}
