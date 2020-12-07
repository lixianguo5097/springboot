package com.lxg.service.impl;

import com.lxg.model.SysUser;
import com.lxg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lxg.mapper.SysUserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public SysUser getUser(String username) {
        return sysUserMapper.selectOne(new SysUser(username));
	}
}
