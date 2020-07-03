package com.lxg.controller;

import com.lxg.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lxg
 * @date 2020/4/8 16:04
 * @description
 */
@Slf4j
@Controller
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/getUser")
    public void getUser() {
        userService.sendUserMsg();
        log.info("getUser");
    }
}
