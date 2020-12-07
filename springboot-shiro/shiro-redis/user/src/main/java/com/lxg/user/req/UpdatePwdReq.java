package com.lxg.user.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName UpdatePwdReq
 * @Description
 * @Author ph
 * @Date 2020/3/18 17:13
 * @Version 1.0
 **/
@Data
public class UpdatePwdReq {


    /**
     * 用户id
     */
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;
    /**
     * 用户id
     */
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
}
