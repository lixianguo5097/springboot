package com.lxg.model.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * User
 * @author dolyw.com
 * @date 2018/8/31 14:43
 */
@TableName("user")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 3342723124953988435L;

    /**
     * ID
     */
    @TableId
    private Integer id;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    private String password;

    /**
     * 昵称
     */
    @NotNull(message = "用户名不能为空")
    private String username;

    /**
     * 注册时间
     */
    @TableField("reg_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date regTime;

    public User(String username) {
        this.username = username;
    }

    public User() {
    }
}