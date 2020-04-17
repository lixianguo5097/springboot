package com.lxg.controller;

import com.lxg.entity.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author LXG
 * @date 2020-4-17
 */
@RestController
@RequestMapping("/xss")
public class XssController {

    @PostMapping(value = "/user")
    public User user(@RequestBody User user) {
        System.out.println(user);
        user.setUsername("<script>alert('xss')</script>");
        user.setPassword("&lt;script&gt;alert('xss')&lt;/script&gt;");
        return user;
    }

    @PostMapping(value = "/postUser")
    public String postUser(String name) {
        System.out.println(name);
        return name;
    }



    @GetMapping(value = "/getUser")
    public String getUser(String name){
        System.out.println(name);
        return name;
    }

    @GetMapping(value = "/t")
    public User t(String name){
        User user = new User();
        user.setUsername("<script>alert('ffsafg')</script>");
        return user;
    }
}
