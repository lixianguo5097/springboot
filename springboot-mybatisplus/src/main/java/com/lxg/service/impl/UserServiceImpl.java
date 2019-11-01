package com.lxg.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lxg.common.PageResult;
import com.lxg.entity.User;
import com.lxg.mapper.UserMapper;
import com.lxg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: 用户实现类
 * @Author: XIANGUO LI
 * @Date: 2019-10-31 13:57
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateById(user);
    }

    @Override
    public void deleteUser(String id) {
        userMapper.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectList(null);
    }

    @Override
    public User findUserById(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> findSearch(Map searchMap) {
        EntityWrapper<User> wrapper = createSearchCondition(searchMap);
        //根据age倒序查询
        wrapper.orderBy(true, "age", false);
        return userMapper.selectList(wrapper);
    }

    @Override
    public PageResult<User> findSearch(Map searchMap, int page, int size) {
        EntityWrapper<User> wrapper = createSearchCondition(searchMap);
        //根据age倒序查询
        wrapper.orderBy(true, "age", false);
        Page<User> userPage = new Page<>(page,size);
        List<User> list = userMapper.selectPage(userPage, wrapper);
        return new PageResult<>(userPage.getTotal(),list);
    }

    @Override
    public List<User> findByXml() {
        return userMapper.findByXml();
    }

    /**
     * 构造查询条件
     * @param searchMap
     * @return
     */
    public EntityWrapper<User> createSearchCondition(Map searchMap) {
        EntityWrapper<User> wrapper = new EntityWrapper<>(new User());
        if (searchMap.get("name") != null) {
            wrapper.eq("name", searchMap.get("name"));
        }
        if (searchMap.get("age") != null) {
            wrapper.eq("age", searchMap.get("age"));
        }
        return wrapper;
    }
}
