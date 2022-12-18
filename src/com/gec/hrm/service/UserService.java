package com.gec.hrm.service;

import com.gec.hrm.pojo.UserInf;
import com.github.pagehelper.PageInfo;

public interface UserService {

	UserInf login(String name, String pwd);

	PageInfo<UserInf> findByPage(Integer pageIndex, UserInf user);

	UserInf findById(int id);

	int update(UserInf user);

	int save(UserInf user);

	void delete(Integer[] userIds, Integer id);
	
	UserInf findByName(String loginname);
}
