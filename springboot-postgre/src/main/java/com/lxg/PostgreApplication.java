package com.lxg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 如果表名与pg内置表名重合，如user表，需要指定哪个数据库的哪个模式的哪个表
 * 如postgres.public.user
 * 启动类
 * @author LXG
 * @date 2019-10-28
 */
@SpringBootApplication
@MapperScan("com.lxg.mapper")
public class PostgreApplication {
    public static void main(String[] args) {
        SpringApplication.run(PostgreApplication.class, args);
    }
}
