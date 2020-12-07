package com.lxg.service;

import com.lxg.model.SysUser;

public interface UserService {

	/**
	 * 用户名查询用户
	 * @param username
	 * @return
	 */
	SysUser getUser(String username);
}
