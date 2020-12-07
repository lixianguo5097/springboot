package com.lxg.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lxg.model.SysRole;
import com.lxg.model.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RoleMapper
 * @author lxg
 * @date 2018/8/31 14:42
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 根据User查询Role
     * @param sysUser
     * @return java.util.List<com.wang.model.SysRole>
     * @author dolyw.com
     * @date 2018/8/31 11:30
     */
    List<SysRole> findRoleByUser(SysUser sysUser);
}