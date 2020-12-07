package com.lxg.user.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lxg.user.model.SysPermission;
import com.lxg.user.mapper.SysPermissionMapper;
import com.lxg.user.model.SysRolePermission;
import com.lxg.user.model.SysUserRole;
import com.lxg.user.service.SysPermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lxg.user.service.SysRolePermissionService;
import com.lxg.user.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author PH
 * @since 2020-04-08
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Autowired
    private SysRolePermissionService rolePermissionService;
    @Autowired
    private SysUserRoleService userRoleService;


    /**
     * 用户可以访问的所有路径
     * @param userId
     * @return
     */
    @Override
    public List<String> userAllPermission(String userId) {
        SysUserRole userRole = new SysUserRole();
        userRole.setUserId(userId);
        List<SysUserRole> userRoleList = userRoleService.selectList(new EntityWrapper<>(userRole));
        List<String> permissionList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(userRoleList)){
            for(SysUserRole userRoleInfo:userRoleList){
                SysRolePermission rolePermission = new SysRolePermission();
                rolePermission.setRoleId(userRoleInfo.getRoleId());
                List<SysRolePermission> rolePermissionList = rolePermissionService.selectList(new EntityWrapper<>(rolePermission));
                for (SysRolePermission rolePermissionInfo : rolePermissionList){
                    SysPermission permission = selectById(rolePermissionInfo.getPermissionId());
                    permissionList.add(permission.getPerCode());
                }
            }
            return permissionList;

        }

        return null;
    }
}
