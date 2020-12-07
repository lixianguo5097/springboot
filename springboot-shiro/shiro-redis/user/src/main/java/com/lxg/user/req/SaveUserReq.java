package com.lxg.user.req;

import lombok.Data;

/**
 * @ClassName SaveUserReq
 * @Description 按条件查询
 * @Author ph
 * @Date 2020/3/18 10:16
 * @Version 1.0
 **/
@Data
public class SaveUserReq{

    /**
     * 用户id
     */
    private String id;
    /**
     * 用户名,登录账号
     */
    private String username;
    /**
     * 电话号码
     */
    private String phone;
    /**
     * 角色类型
     */
    private Integer roleType;
}
