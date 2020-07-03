package com.lxg.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lxg
 * @date 2020/4/8 16:22
 * @description
 */
@Data
public class User implements Serializable {

    /**
     * id
     */
    private Integer id;

    /**
     * 用户名字
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
