package com.lxg.user.service;

import com.lxg.user.model.SysPermission;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author PH
 * @since 2020-04-08
 */
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 用户可以访问的所有路径
     *
     * @param userId
     * @return
     */
    List<String> userAllPermission(String userId);
}
