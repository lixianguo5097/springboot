package com.lxg;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.lxg.xss.XssStringJsonSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * 这里防止XSS攻击使用的方法是对可能造成漏洞的特殊字符进行转义，
 * Json格式和其他格式区分开了，
 * Json是对参数重新序列化的时候转义，这是在数据返回的时候进行的
 * 一般格式是通过过滤器进行转义，这是在数据获取的时候进行的
 * 特殊字符的转义只需要在数据获取或返回的一方进行即可
 * @author LXG
 * @date 2020-4-17
 */
@SpringBootApplication
@ServletComponentScan(basePackages = "com.lxg.xss")
public class XssApplication {
    public static void main(String[] args) {
        SpringApplication.run(XssApplication.class, args);
    }

    /**
     * 过滤json类型的
     * @param builder
     * @return
     */
    @Bean
    @Primary
    public ObjectMapper xssObjectMapper(Jackson2ObjectMapperBuilder builder) {
        // 解析器
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        // 注册xss解析器
        SimpleModule xssModule = new SimpleModule();
        xssModule.addSerializer(new XssStringJsonSerializer());
        objectMapper.registerModule(xssModule);
        // 返回
        return objectMapper;
    }
}
