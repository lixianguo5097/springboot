package com.lxg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 入门controller
 * @author LXG
 * @date 2019-10-28
 */
@RestController
public class HelloWordController {
    @GetMapping("/hello")
    public String index() {
        return "Hello World";
    }
}
