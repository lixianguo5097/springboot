package com.lxg.user.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lxg.user.mapper.SysUserRoleMapper;
import com.lxg.user.model.SysRole;
import com.lxg.user.mapper.SysRoleMapper;
import com.lxg.user.model.SysUserRole;
import com.lxg.user.service.SysRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author PH
 * @since 2020-04-08
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    /**
     * 查询每个用户对应的所有角色
     * @param userId
     * @return List<Role>
     */
    @Override
    public List<String> getRoleListByUserId(String userId) {
        SysUserRole userRole = new SysUserRole();
        userRole.setUserId(userId);
        List<SysUserRole> userRoleList = userRoleMapper.selectList(new EntityWrapper<>(userRole));
        if(!CollectionUtils.isEmpty(userRoleList)){
            List<String> roleNameList = new ArrayList<>(userRoleList.size());
            for(SysUserRole userRoleInfo:userRoleList){
                SysRole role = selectById(userRoleInfo.getRoleId());
                roleNameList.add(role.getName());
            }
            return roleNameList;
        }

        return null;
    }
}
