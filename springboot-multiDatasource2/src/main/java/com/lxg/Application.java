package com.lxg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 使用mybatis-Plus集成多数据源
 * 只需要加入dynamic-datasource-spring-boot-starter依赖
 * 在yml文件进行配置，在方法上加使用@Ds()注解，值为配置文件中的参数，不加默认为主库
 * @author LXG
 * @date 2021-12-2
 */
@SpringBootApplication(scanBasePackages = "com.lxg")
@MapperScan("com.lxg.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
