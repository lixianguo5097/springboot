package com.lxg.handler;


import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lixio on 2019/3/28 15:44
 * @description 用以mysql中json格式的字段，进行转换的自定义转换器，转换为实体类的JSONObject属性
 * MappedTypes注解中的类代表此转换器可以自动转换为的java对象
 * MappedJdbcTypes注解中设置的是对应的jdbctype
 */

@MappedTypes(JSONObject.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class JSONTypeHandler extends BaseTypeHandler<JSONObject>{

    //设置非空参数
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, JSONObject parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, String.valueOf(parameter.toJSONString()));
    }
    //根据列名，获取可以为空的结果
    @Override
    public JSONObject getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String sqlJson = rs.getString(columnName);
        if (null != sqlJson){
            return JSONObject.parseObject(sqlJson);
        }
        return null;
    }
    //根据列索引，获取可以为空的结果
    @Override
    public JSONObject getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String sqlJson = rs.getString(columnIndex);
        if (null != sqlJson){
            return JSONObject.parseObject(sqlJson);
        }
        return null;
    }

    @Override
    public JSONObject getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String sqlJson = cs.getString(columnIndex);
        if (null != sqlJson){
            return JSONObject.parseObject(sqlJson);
        }
        return null;
    }
}
