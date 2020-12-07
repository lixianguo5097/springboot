package com.lxg.api.controller;

import com.lxg.api.aop.SysLogAop;
import com.lxg.common.base.BaseResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LXG
 * @date 2020-12-4
 */

@RestController
public class WebController {
    @GetMapping("/article")
    @SysLogAop(operation = "查询", operateModule = "文章")
    public BaseResult article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return BaseResult.success("You are already logged in");
        } else {
            return BaseResult.success("You are guest");
        }
    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
    public BaseResult requireAuth() {
        return BaseResult.success("You are authenticated");
    }

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public BaseResult requireRole() {
        return BaseResult.success("You are visiting require_role");
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"user:view", "user:edit"})
    public BaseResult requirePermission() {
        return BaseResult.success("You are visiting permission require edit,view");
    }
}
