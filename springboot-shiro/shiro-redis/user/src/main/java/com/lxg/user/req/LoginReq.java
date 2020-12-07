package com.lxg.user.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName LoginReq
 * @Description
 * @Author LiuLP
 * @Date 2019/7/26 0:36
 * @Version 1.0
 **/
@Data
public class LoginReq {


    @NotBlank(message ="用户名不能为空")
    private String username;

    @NotBlank(message ="密码不能为空")
    private String password;
}
