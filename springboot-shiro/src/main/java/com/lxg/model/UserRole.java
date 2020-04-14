package com.lxg.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * UserRole
 * @author dolyw.com
 * @date 2018/8/31 14:43
 */
@TableName("user_role")
@Data
public class UserRole implements Serializable {

    private static final long serialVersionUID = -3397516891053950951L;

    /**
     * ID
     */
    @TableId
    private Integer id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 角色id
     */
    @TableField("role_id")
    private Integer roleId;

}