package com.lxg.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * 用户实体类
 * @author XIANGUO LI
 * @date 2019-10-28 17:33
 */
@Data
public class User implements Serializable {
    /** id */
    private String id;
    /** 姓名 */
    private String name;
    /** 年龄 */
    private Integer age;
}
