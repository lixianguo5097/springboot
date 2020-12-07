package com.lxg.config;

import com.lxg.mapper.SysPermissionMapper;
import com.lxg.mapper.SysRoleMapper;
import com.lxg.model.SysPermission;
import com.lxg.model.SysRole;
import com.lxg.model.SysUser;
import com.lxg.service.UserService;
import com.lxg.util.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;



    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("授权 doGetAuthorizationInfo");
        String username = JWTUtil.getUsername(principals.getPrimaryPrincipal().toString());
        SysUser sysUser = userService.getUser(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 查询用户角色
        List<SysRole> sysRoles = sysRoleMapper.findRoleByUser(new SysUser(sysUser.getUsername()));
        for (SysRole sysRole : sysRoles) {
            if (sysRole != null) {
                // 添加角色
                simpleAuthorizationInfo.addRole(sysRole.getName());
                // 根据用户角色查询权限
                List<SysPermission> sysPermissions = sysPermissionMapper.findPermissionByRole(sysRole);
                for (SysPermission sysPermission : sysPermissions) {
                    if (sysPermission != null) {
                        // 添加权限
                        simpleAuthorizationInfo.addStringPermission(sysPermission.getPerCode());
                    }
                }
            }
        }
        return simpleAuthorizationInfo;

    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) {
        System.out.println("认证 doGetAuthenticationInfo");
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }

        SysUser sysUserBean = userService.getUser(username);
        if (sysUserBean == null) {
            throw new AuthenticationException("SysUser didn't existed!");
        }

        if (! JWTUtil.verify(token, username, sysUserBean.getPassword())) {
            throw new AuthenticationException("Username or password error");
        }
        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
