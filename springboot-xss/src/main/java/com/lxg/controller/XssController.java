package com.lxg.controller;

import com.lxg.model.XssUser;
import org.springframework.web.bind.annotation.*;

/**
 * @author LXG
 * @date 2020-4-17
 */
@RestController
@RequestMapping("/xss")
public class XssController {

    @PostMapping(value = "/user")
    public XssUser user(@RequestBody XssUser xssUser) {
        System.out.println(xssUser);
        xssUser.setUsername("<script>alert('xss')</script>");
        xssUser.setPassword("&lt;script&gt;alert('xss')&lt;/script&gt;");
        return xssUser;
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
    public XssUser t(String name){
        XssUser xssUser = new XssUser();
        xssUser.setUsername("<script>alert('ffsafg')</script>");
        return xssUser;
    }
}
