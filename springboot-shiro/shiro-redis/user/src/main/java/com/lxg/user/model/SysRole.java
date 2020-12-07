package com.lxg.user.model;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * SysRole
 * @author lxg
 * @date 2018/8/31 14:42
 */
@Data
public class SysRole implements Serializable {

    private static final long serialVersionUID = 6382925944937625109L;

    /**
     * ID
     */
    @TableId
    private String id;

    /**
     * 角色名称
     */
    private String name;

}