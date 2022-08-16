package com.lxg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxg.common.PageResult;
import com.lxg.mapper.MyBatisUserMapper;
import com.lxg.model.MybatisUser;
import com.lxg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 用户实现类
 * @author LXG
 * @date 2019-10-31
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private MyBatisUserMapper myBatisUserMapper;

    @Override
    public void addUser(MybatisUser mybatisUser) {
        myBatisUserMapper.insert(mybatisUser);
    }

    @Override
    public void updateUser(MybatisUser mybatisUser) {
        myBatisUserMapper.updateById(mybatisUser);
    }

    @Override
    public void deleteUser(String id) {
        myBatisUserMapper.deleteById(id);
    }

    @Override
    public List<MybatisUser> findAll() {
        return myBatisUserMapper.selectList(null);
    }

    @Override
    public MybatisUser findUserById(String id) {
        return myBatisUserMapper.selectById(id);
    }

    @Override
    public List<MybatisUser> findSearch(Map searchMap) {
        LambdaQueryWrapper<MybatisUser> wrapper = createSearchCondition(searchMap);
        //根据age倒序查询
        wrapper.orderByDesc(MybatisUser::getAge);
        return myBatisUserMapper.selectList(wrapper);
    }

    @Override
    public PageResult<MybatisUser> findSearch(Map searchMap, int page, int size) {
        LambdaQueryWrapper<MybatisUser> wrapper = createSearchCondition(searchMap);
        //根据age倒序查询
        wrapper.orderByDesc(MybatisUser::getAge);
        Page<MybatisUser> mybatisUserPage = myBatisUserMapper.selectPage(new Page<>(page,size), wrapper);
        return new PageResult<>(mybatisUserPage.getTotal(),mybatisUserPage.getRecords());
    }

    @Override
    public List<MybatisUser> findByXml() {
        return myBatisUserMapper.findByXml();
    }

    /**
     * 构造查询条件
     * @param searchMap
     * @return
     */
    public LambdaQueryWrapper<MybatisUser> createSearchCondition(Map searchMap) {
        LambdaQueryWrapper<MybatisUser> wrapper = new LambdaQueryWrapper<>(new MybatisUser());
        if (searchMap.get("name") != null) {
            wrapper.eq(MybatisUser::getName, searchMap.get("name"));
        }
        if (searchMap.get("age") != null) {
            wrapper.eq(MybatisUser::getAge, searchMap.get("age"));
        }
        return wrapper;
    }
}
