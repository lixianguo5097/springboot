package com.lxg.user.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName SelectUserResp
 * @Description
 * @Author ph
 * @Date 2020/3/18 11:05
 * @Version 1.0
 **/
@Data
public class SelectUserResp {

    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户真实姓名
     */
    private String realName;
    /**
     * 电话号码
     */
    private String phone;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createTime;
    /**
     * 角色 1. 管理员  2.普通用户
     */
    private String role;
}
