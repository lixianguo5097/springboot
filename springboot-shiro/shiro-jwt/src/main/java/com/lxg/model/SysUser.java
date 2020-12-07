package com.lxg.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * SysUser
 * @author lxg
 * @date 2018/8/31 14:43
 */
@Data
public class SysUser implements Serializable {

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

    public SysUser(String username) {
        this.username = username;
    }

    public SysUser() {
    }
}