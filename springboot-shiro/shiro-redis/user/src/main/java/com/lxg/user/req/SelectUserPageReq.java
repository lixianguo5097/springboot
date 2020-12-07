package com.lxg.user.req;

import com.lxg.common.base.BasePageReq;
import lombok.Data;

/**
 * @ClassName SelectUserPageReq
 * @Description 按条件查询
 * @Author ph
 * @Date 2020/3/18 10:16
 * @Version 1.0
 **/
@Data
public class SelectUserPageReq extends BasePageReq {

    /**
     * 登录账号或者真实姓名
     */
    private String searchContent;

    /**
     * 角色id,1：管理员，2：普通用户
     */
    private Integer roleId;
}
