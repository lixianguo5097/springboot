package com.lxg.model.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Permission
 * @author dolyw.com
 * @date 2018/8/31 14:41
 */
@TableName("permission")
@Data
public class Permission implements Serializable {

    private static final long serialVersionUID = -8834983208597107688L;

    /**
     * ID
     */
    @TableId
    private Integer id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 权限代码字符串
     */
    @TableField("per_code")
    private String perCode;
}