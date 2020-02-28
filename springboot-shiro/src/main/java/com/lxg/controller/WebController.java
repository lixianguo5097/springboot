package com.lxg.controller;

import com.lxg.aop.SysLogAop;
import com.lxg.model.Result;
import com.lxg.exception.BizException;
import com.lxg.model.entity.User;
import com.lxg.service.UserService;
import com.lxg.util.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Result login(@RequestBody User u) {
        User user = userService.getUser( u.getUsername());
        if (user == null) {
            throw new BizException(401,"该用户不存在");
        }
        if (user.getPassword().equals( u.getPassword())) {
            return new Result(200, "Login success", JWTUtil.sign( u.getUsername(), u.getPassword()));
        } else {
            throw new BizException(401,"Unauthorized登录失败");
        }
    }

    @GetMapping("/article")
    @SysLogAop(operation = "查询",operateModule = "文章")
    public Result article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return new Result(200, "You are already logged in");
        } else {
            return new Result(200, "You are guest");
        }
    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
    public Result requireAuth() {
        return new Result(200, "You are authenticated");
    }

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public Result requireRole() {
        try {
            return new Result(200, "You are visiting require_role");
        } catch (Exception e) {
            throw new BizException(400, e.getMessage());
        }
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"user:view", "user:edit"})
    public Result requirePermission() {
        return new Result(200, "You are visiting permission require edit,view");
    }

    @RequestMapping(path = "/401")
    public Result unauthorized() {
        return new Result(401, "Unauthorized未授权");
    }
}
