package com.lxg.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lxg.handler.JsonArrayTypeHandler;
import com.lxg.handler.ArrayTypeHandler;
import com.lxg.handler.JSONTypeHandler;
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
    @TableId
    String id;
    //数据库类型为varchar
    @TableField(value = "detail", jdbcType = JdbcType.VARCHAR, typeHandler = JSONTypeHandler.class)
    JSONObject detail;
    //数据库类型为varchar
    @TableField(value = "scores", jdbcType = JdbcType.ARRAY, typeHandler = ArrayTypeHandler.class)
    Object[] scores;
    //数据库类型为varchar
    @TableField(value = "school", jdbcType = JdbcType.VARCHAR, typeHandler = JsonArrayTypeHandler.class)
    JSONArray school;

}
