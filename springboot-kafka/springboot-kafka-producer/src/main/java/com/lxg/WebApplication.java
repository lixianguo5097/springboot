package com.lxg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lxg
 * @date 2020/4/8 15:59
 * @description 如果kafka中没有配置文件中设置的topic，则会自动创建一个topic
 * 微服务中controller层作为服务调用方其实应该与service层拆分，这里为了方便就没有这么做
 */
@SpringBootApplication
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
