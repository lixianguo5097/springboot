package com.lxg.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lxg.model.User;
import com.lxg.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RoleMapper
 * @author dolyw.com
 * @date 2018/8/31 14:42
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据User查询Role
     * @param user
     * @return java.util.List<com.wang.model.Role>
     * @author dolyw.com
     * @date 2018/8/31 11:30
     */
    List<Role> findRoleByUser(User user);
}