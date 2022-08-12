package com.lxg.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lxg.handler.ArrayTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.JdbcType;

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
@TableName(value = "student", autoResultMap = true)
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableField(value = "serial_id")
    @TableId
    String serialId;
    @TableField(value = "name", jdbcType = JdbcType.ARRAY, typeHandler = ArrayTypeHandler.class)
    String[] name;
    @TableField(value = "scores", jdbcType = JdbcType.ARRAY, typeHandler = ArrayTypeHandler.class)
    Integer[] scores;


}
