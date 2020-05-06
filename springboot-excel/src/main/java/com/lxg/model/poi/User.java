package com.lxg.model.poi;

import lombok.Data;

/**
 * @author LXG
 * @date 2020-4-26
 */
@Data
public class User {
    private String studentId;
    private String name;
    private Integer age;
    private String sex;

    public User(String studentId, String name, Integer age, String sex) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public User() {
    }
}
