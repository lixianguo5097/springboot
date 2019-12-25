package com.lxg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author LXG
 * @date 2019-12-11
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //定义扫描接口的包
                .apis(RequestHandlerSelectors.basePackage("com.lxg.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //定义界面标题
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                //定义界面描述
                .description("swagger2-文档构建利器")
                .termsOfServiceUrl("https://www.lxgblog.com/")
                //作者
                .contact("李先国")
                //版本
                .version("1.0")
                .build();
    }
}
