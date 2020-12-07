package com.lxg.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lxg.common.base.BaseResult;
import com.lxg.common.exception.MyException;
import com.lxg.user.model.SysUser;
import com.lxg.user.req.DelUserReq;
import com.lxg.user.req.SaveUserReq;
import com.lxg.user.req.SelectUserPageReq;
import com.lxg.user.req.UpdatePwdReq;
import com.lxg.user.resp.SelectUserResp;
import com.lxg.user.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName UserController
 * @Author lxg
 * @Version 1.0
 * @Description
 * @Date 2020/4/26 13:34
 */
@Slf4j
@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private SysUserService userService;



    @PostMapping("/list")
    @ResponseBody
    public BaseResult selectUserByPage(@RequestBody SelectUserPageReq req) {
        return userService.findUserByPage(req);
    }

    @PostMapping("/add")
    @ResponseBody
    public BaseResult saveUser(@RequestBody SaveUserReq req) {
        List<SysUser> realName = userService.selectList(new EntityWrapper<SysUser>().eq("username", req.getUsername()));
        if (realName.size() > 0) {
            throw new MyException(-1, "账号名已存在");
        }
        return userService.saveOrUpdateUserInfo(req);
    }

    @PostMapping("/update")
    @ResponseBody
    public BaseResult updateUser(@RequestBody SaveUserReq req) {
        List<SysUser> realName = userService.selectList(new EntityWrapper<SysUser>().eq("username", req.getUsername()));
        if (req.getId()==null&&realName.size() > 0) {
            throw new MyException(-1, "账号名已存在");
        }
        return userService.saveOrUpdateUserInfo(req);
    }

    @PostMapping("/resetPwd")
    @ResponseBody
    public BaseResult resetPassword(@Valid @RequestBody DelUserReq req) {
        return userService.resetPassword(req);
    }

    @PostMapping("/del")
    @ResponseBody
    public BaseResult delUser(@Valid @RequestBody DelUserReq req) {
        return userService.delUser(req);
    }

    @PostMapping("/check")
    @ResponseBody
    public BaseResult checkUsersInfoById(@Valid @RequestBody DelUserReq req) {
        SelectUserResp resp = userService.selectUserById(req);
        return BaseResult.success(resp);
    }

    @PostMapping("/updatePwd")
    @ResponseBody
    public BaseResult updatePassword(@Valid @RequestBody UpdatePwdReq req) {
        return userService.updatePassword(req);
    }

}
