package com.lxg.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lxg.common.PageResult;
import com.lxg.model.MybatisUser;
import com.lxg.mapper.MyBatisUserMapper;
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
        EntityWrapper<MybatisUser> wrapper = createSearchCondition(searchMap);
        //根据age倒序查询
        wrapper.orderBy(true, "age", false);
        return myBatisUserMapper.selectList(wrapper);
    }

    @Override
    public PageResult<MybatisUser> findSearch(Map searchMap, int page, int size) {
        EntityWrapper<MybatisUser> wrapper = createSearchCondition(searchMap);
        //根据age倒序查询
        wrapper.orderBy(true, "age", false);
        Page<MybatisUser> userPage = new Page<>(page,size);
        List<MybatisUser> list = myBatisUserMapper.selectPage(userPage, wrapper);
        return new PageResult<>(userPage.getTotal(),list);
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
    public EntityWrapper<MybatisUser> createSearchCondition(Map searchMap) {
        EntityWrapper<MybatisUser> wrapper = new EntityWrapper<>(new MybatisUser());
        if (searchMap.get("name") != null) {
            wrapper.eq("name", searchMap.get("name"));
        }
        if (searchMap.get("age") != null) {
            wrapper.eq("age", searchMap.get("age"));
        }
        return wrapper;
    }
}
