package com.lxg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 入门controller
 * @Author: XIANGUO LI
 * @Date: 2019-10-28 16:23
 */
@RestController
public class HelloWordController {
    @GetMapping("/hello")
    public String index() {
        return "Hello World";
    }
}
