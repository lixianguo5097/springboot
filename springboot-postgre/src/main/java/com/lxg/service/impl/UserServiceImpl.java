package com.lxg.service.impl;

import com.lxg.entity.User;
import com.lxg.mapper.UserMapper;
import com.lxg.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lxg
 * @since 2022-08-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
