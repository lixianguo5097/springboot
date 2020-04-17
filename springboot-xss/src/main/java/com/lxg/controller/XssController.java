package com.lxg.controller;

import com.lxg.entity.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author LXG
 * @date 2020-4-17
 */
@RestController
@RequestMapping("/xss")
public class XssController {

    @PostMapping(value = "/user")
    public User user(@RequestBody User user) {
        return user;
    }

    @PostMapping(value = "/postUser")
    public String postUser(String name) {
        return name;
    }



    @GetMapping(value = "/getUser")
    public String getUser(String name){
        return name;
    }
}
