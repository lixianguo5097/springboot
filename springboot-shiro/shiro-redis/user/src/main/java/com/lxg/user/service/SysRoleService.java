package com.lxg.user.service;

import com.lxg.user.model.SysRole;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author PH
 * @since 2020-04-08
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 查询每个用户对应的所有角色名称
     *
     * @param userId
     * @return List
     */
    List<String> getRoleListByUserId(String userId);
}
