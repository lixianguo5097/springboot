package com.lxg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LXG
 * @date 2022-4-15
 */
@RestController
@RequestMapping("/src/main/docker")
public class DockerController {
    @GetMapping
    public String docker() {
        return "docker启动项目成功";
    }
}
