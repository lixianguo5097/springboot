package com.lxg.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Role
 * @author dolyw.com
 * @date 2018/8/31 14:42
 */
@TableName("role")
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = 6382925944937625109L;

    /**
     * ID
     */
    @TableId
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

}