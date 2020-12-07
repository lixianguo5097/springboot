package com.lxg.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lxg.api.aop.SysLogAop;
import com.lxg.common.base.BaseResult;
import com.lxg.user.model.SysUser;
import com.lxg.user.model.SysUserRole;
import com.lxg.user.req.LoginReq;
import com.lxg.user.service.SysUserRoleService;
import com.lxg.user.service.SysUserService;
import com.lxg.user.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lxg
 * @date 2020-4-21
 */
@RestController
@RequestMapping("/api/sys")
@Slf4j
public class LoginController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @RequestMapping("/needLogin")
    @ResponseBody
    public BaseResult needLogin(){

        return BaseResult.error(-2,"用户没有权限，请到跳转到登录页面");

    }

    @RequestMapping("/notPermit")
    @ResponseBody
    public BaseResult notPermit(){
        return BaseResult.error(-3,"温馨提示：拒绝访问，没权限");
    }

    /**
     * 登录接口
     * @param req
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    @SysLogAop(operation = "登录",operateModule = "用户模块")
    public BaseResult login(@Valid @RequestBody LoginReq req){

        Subject subject = SecurityUtils.getSubject();
        Map<String,Object> info = new HashMap<>(6);
        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(req.getUsername(), req.getPassword());

            subject.login(usernamePasswordToken);

            info.put("token", subject.getSession().getId());
            String userName = ShiroUtils.getUserName();
            info.put("userName",userName);
            String userId = ShiroUtils.getUserId();
            SysUser userInfo = sysUserService.selectById(userId);
            if(userInfo != null){
                info.put("id",userInfo.getId());
            }
            //查询该用户角色
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(userId);
            SysUserRole sysUserRole = sysUserRoleService.selectOne(new EntityWrapper<>(userRole));
            if(sysUserRole != null){
                info.put("role",sysUserRole.getRoleId());
            }

            return BaseResult.success("登录成功",info);

        }catch (Exception e){
            e.printStackTrace();
            return BaseResult.error("账号或者密码错误");

        }

    }

    /**
     * 退出登录接口
     * @return
     */
    @PostMapping("/logout")
    public BaseResult logout() {
        sysUserService.logout();
        return BaseResult.success("成功退出");
    }

}
