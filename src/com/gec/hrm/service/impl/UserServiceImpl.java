package com.gec.hrm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gec.hrm.mapper.UserInfMapper;
import com.gec.hrm.pojo.UserInf;
import com.gec.hrm.pojo.UserInfExample;
import com.gec.hrm.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserInfMapper userInfMapper;
	
	@Override
	public UserInf login(String name, String pwd) {
		UserInfExample ue = new UserInfExample();
		UserInfExample.Criteria cri = ue.createCriteria();
		cri.andLoginnameEqualTo(name);
		cri.andPasswordEqualTo(pwd);
		List<UserInf> list = userInfMapper.selectByExample(ue);
		if(list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public PageInfo<UserInf> findByPage(Integer pageIndex, UserInf user) {
		UserInfExample ue = new UserInfExample();
		UserInfExample.Criteria cri = ue.createCriteria();
		if(user.getLoginname()!=null&&!user.getLoginname().equals(""))
			cri.andLoginnameLike("%"+user.getLoginname()+"%");
		if(user.getUsername()!=null&&!user.getUsername().equals(""))
			cri.andUsernameLike("%"+user.getUsername()+"%");
		if(user.getStatus()!=null&&user.getStatus()>0)
			cri.andStatusEqualTo(user.getStatus());
		PageHelper.startPage(pageIndex, 3);
		
		List<UserInf> list = userInfMapper.selectByExample(ue);
		PageInfo<UserInf> info =  new PageInfo<>(list);
		return info;
	}

	@Override
	public UserInf findById(int id) {
		// TODO Auto-generated method stub
		return userInfMapper.selectByPrimaryKey(id);
	}

	@Override
	public int update(UserInf user) {
		// TODO Auto-generated method stub
		return userInfMapper.updateByPrimaryKey(user);
	}

	@Override
	public int save(UserInf user) {
		// TODO Auto-generated method stub
		return userInfMapper.insert(user);
	}

	@Override
	public void delete(Integer[] userIds,Integer id) {
		for (Integer ids : userIds) {
			if(ids!=id)
				userInfMapper.deleteByPrimaryKey(ids);
		}
	}

	@Override
	public UserInf findByName(String loginname) {
		UserInfExample ue = new UserInfExample();
		UserInfExample.Criteria cri = ue.createCriteria();
		cri.andLoginnameEqualTo(loginname);
		List<UserInf> list = userInfMapper.selectByExample(ue);
		if(list.size()>0)
			return list.get(0);
		return null;
	}

}
