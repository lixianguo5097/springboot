package com.lxg.user.service;

import com.lxg.user.model.SysUserRole;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 用户-角色关联表 服务类
 * </p>
 *
 * @author PH
 * @since 2020-04-08
 */
public interface SysUserRoleService extends IService<SysUserRole> {
    /**
     * 根据角色查询用户id list
     * 角色： 1-管理员 2-普通用户
     *
     * @param roleType
     * @return
     */
    List<String> selectUserIdListByRole(Integer roleType);
}
