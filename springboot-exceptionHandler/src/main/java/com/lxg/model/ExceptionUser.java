package com.lxg.model;

import lombok.Data;

import java.io.Serializable;


/**
 * 用户实体类
 * @author LXG
 * @date 2019-10-28
 */
@Data
public class ExceptionUser implements Serializable {
    /** id */
    private String id;
    /** 姓名 */
    private String name;
    /** 年龄 */
    private Integer age;
}
