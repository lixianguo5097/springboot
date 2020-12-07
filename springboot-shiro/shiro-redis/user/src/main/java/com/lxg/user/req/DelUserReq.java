package com.lxg.user.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName DelUserReq
 * @Description
 * @Author ph
 * @Date 2020/3/18 16:09
 * @Version 1.0
 **/
@Data
public class DelUserReq {

    /**
     * 用户id
     */
    @NotBlank(message = "用户id不能为空")
    private String id;
}
