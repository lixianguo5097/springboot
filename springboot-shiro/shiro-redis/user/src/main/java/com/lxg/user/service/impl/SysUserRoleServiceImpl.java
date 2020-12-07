package com.lxg.user.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lxg.user.model.SysUserRole;
import com.lxg.user.mapper.SysUserRoleMapper;
import com.lxg.user.service.SysUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户-角色关联表 服务实现类
 * </p>
 *
 * @author PH
 * @since 2020-04-08
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    /**
     * 根据角色查询用户id list
     * 角色： 1-管理员 2-普通用户
     * @param roleType
     * @return
     */
    @Override
    public List<String> selectUserIdListByRole(Integer roleType) {
        SysUserRole sysUserRole = new SysUserRole();
        if(roleType != null){
            sysUserRole.setRoleId(roleType.toString());
        }
        List<SysUserRole> userRoleList = selectList(new EntityWrapper<>(sysUserRole));
        List<String> userIdList = new ArrayList<>(userRoleList.size());
        if(!CollectionUtils.isEmpty(userRoleList)){
            for (SysUserRole userRole: userRoleList) {
                userIdList.add(userRole.getUserId());
            }
        }
        return userIdList;
    }
}
