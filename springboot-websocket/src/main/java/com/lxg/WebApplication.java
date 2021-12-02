package com.lxg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1.页面使用的thymeleaf
 * 2.静态页面使用了热部署，在HTML页面使用Ctrl+shift+F9可以直接刷新修改的页面
 * 不需要重启服务
 * @author LXG
 * @date 2021-12-2
 */
@SpringBootApplication
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
