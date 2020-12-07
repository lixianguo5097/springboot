package com.lxg.api.config;


import com.lxg.user.model.SysUser;
import com.lxg.user.service.SysPermissionService;
import com.lxg.user.service.SysRoleService;
import com.lxg.user.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;

/**
 * @ClassName CustomRealm
 * @Author lxg
 * @Version 1.0
 * @Description 自定义realm
 * @Date 2020/3/6 15:10
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private SysUserService userService;
    @Autowired
    @Lazy
    private SysRoleService roleService;
    @Autowired
    @Lazy
    private SysPermissionService permissionService;

    /**
     * 进行权限校验的时候回调用
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("授权 doGetAuthorizationInfo");
        SysUser newUser = (SysUser)principals.getPrimaryPrincipal();
        SysUser user = userService.findUserByName(newUser.getUsername());

        //拥有的角色名称
        List<String> stringRoleNameList = roleService.getRoleListByUserId(user.getId());
        //该用户允许访问的全部路径
        List<String> stringPermissionList = permissionService.userAllPermission(user.getId());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(stringRoleNameList);
        simpleAuthorizationInfo.addStringPermissions(stringPermissionList);
        return simpleAuthorizationInfo;

    }


    /**
     * 用户登录的时候会调用
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("认证 doGetAuthenticationInfo");
        //从token获取用户信息，token代表用户输入
        String username = (String)token.getPrincipal();
        SysUser user =  userService.findUserByName(username);
        //取密码
        String pwd = user.getPassword();
        if(pwd == null || "".equals(pwd)){
            return null;
        }
        //如果不是用shiro-Redis的插件的话，而是用普通redis时，下面不可以用user对象，而是传username
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
    }
}
