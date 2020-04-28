package com.lxg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * HSSFWorkbook:是操作Excel2003以前（包括2003）的版本，扩展名是.xls；
 * XSSFWorkbook:是操作Excel2007后的版本，扩展名是.xlsx
 *
 * 访问 http://localhost:8080/file.html 测试页面
 *
 * @author LXG
 * @date 2020-4-23
 */
@SpringBootApplication
public class PoiApplication {
    public static void main(String[] args) {
        SpringApplication.run(PoiApplication.class, args);
    }
}

