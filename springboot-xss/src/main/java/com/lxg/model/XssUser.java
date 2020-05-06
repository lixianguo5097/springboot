package com.lxg.model;

import lombok.Data;

/**
 * @author LXG
 * @date 2020-4-17
 */
@Data
public class XssUser {
    private Integer id;
    private String username;
    private String password;
}
