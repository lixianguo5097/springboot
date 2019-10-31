package com.lxg.springboot.dao.mybatisplus.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lxg.springboot.dao.mybatisplus.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 用户Mapper
 * @Author: XIANGUO LI
 * @Date: 2019-10-31 11:42
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    List<User> findByXml();
}
