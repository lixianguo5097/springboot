package com.lxg.service.impl;

import com.lxg.model.User;
import com.lxg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lxg.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUser(String username) {
        return userMapper.selectOne(new User(username));
	}
}
