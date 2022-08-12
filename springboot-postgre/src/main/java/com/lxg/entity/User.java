package com.lxg.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lxg
 * @since 2022-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(" postgres.public.user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String id;

    private String username;

    private String password;


}
