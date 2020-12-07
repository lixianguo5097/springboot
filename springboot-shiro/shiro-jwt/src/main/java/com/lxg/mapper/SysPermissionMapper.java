package com.lxg.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lxg.model.SysPermission;
import com.lxg.model.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PermissionMapper
 *
 * @author lxg
 * @date 2018/8/31 14:42
 */
@Repository
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    /**
     * 根据Role查询Permission
     *
     * @param sysRole
     * @return java.util.List<com.wang.model.SysPermission>
     * @author dolyw.com
     * @date 2018/8/31 11:30
     */
    List<SysPermission> findPermissionByRole(SysRole sysRole);
}