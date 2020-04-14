package com.lxg.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lxg.model.Permission;
import com.lxg.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PermissionMapper
 *
 * @author dolyw.com
 * @date 2018/8/31 14:42
 */
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 根据Role查询Permission
     *
     * @param role
     * @return java.util.List<com.wang.model.Permission>
     * @author dolyw.com
     * @date 2018/8/31 11:30
     */
    List<Permission> findPermissionByRole(Role role);
}