package com.lxg.model;

import lombok.Data;

/**
 * @author LXG
 * @date 2019-12-11
 */
@Data
public class SwaggerUser {
    private Long id;
    /** 姓名 */
    private String name;

    /** 年龄 */
    private Integer age;

    public SwaggerUser() {
    }

    public SwaggerUser(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
