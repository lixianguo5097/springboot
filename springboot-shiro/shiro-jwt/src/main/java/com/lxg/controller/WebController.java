package com.lxg.controller;

import com.lxg.aop.SysLogAop;
import com.lxg.config.Result;
import com.lxg.exception.MyException;
import com.lxg.model.SysUser;
import com.lxg.service.UserService;
import com.lxg.util.JWTUtil;
import com.lxg.util.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 需要验证权限的，在请求头添加Authorization:token
 * @author LXG
 * @date 2020-2-21
 */
@RestController
public class WebController {

    private UserService userService;

    @Autowired
    public void setService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @SysLogAop(operation = "登录",operateModule = "用户模块")
    public Result login(@RequestBody SysUser u) {
        SysUser sysUser = userService.getUser( u.getUsername());
        if (sysUser == null) {
            throw new MyException(401,"该用户不存在");
        }
        String md5Pwd = new Md5Hash(u.getPassword(), null, 2).toString();
        if (sysUser.getPassword().equals(md5Pwd)) {
            return Result.success(JWTUtil.sign( u.getUsername(),md5Pwd));
        } else {
            throw new MyException(401,"Unauthorized登录失败");
        }
    }

    @GetMapping("/article")
    @SysLogAop(operation = "查询",operateModule = "文章")
    public Result article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return Result.success("You are already logged in");
        } else {
            return Result.success("You are guest");
        }
    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
    public Result requireAuth() {
        return Result.success("You are authenticated");
    }

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public Result requireRole() {
        try {
            return Result.success("You are visiting require_role");
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"user:view", "user:edit"})
    public Result requirePermission() {
        return Result.success("You are visiting permission require edit,view");
    }

//    @RequestMapping(path = "/401")
//    public Result unauthorized() {
//        throw MyException.newException(401, "Unauthorized未授权");
//    }
}
