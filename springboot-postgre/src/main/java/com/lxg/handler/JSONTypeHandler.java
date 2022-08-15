package com.lxg.handler;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.postgresql.util.PGobject;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Oct.Ca
 * @version 1.0
 * @date 2020/8/6 11:34
 * postgres中json格式的字段，进行转换的自定义转换器，转换为实体类的JSONObject属性
 * MappedTypes注解中的类代表此转换器可以自动转换为的java对象
 */
@MappedTypes(JSONObject.class)
public class JSONTypeHandler extends BaseTypeHandler<JSONObject> {
    //引入PGSQL提供的工具类PGobject
    private static final PGobject jsonObject = new PGobject();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, JSONObject param, JdbcType jdbcType) throws SQLException {
        //转换的操作在这里!!!
        jsonObject.setType("json");
        jsonObject.setValue(param.toString());
        ps.setObject(i, jsonObject);
    }

    @Override
    public JSONObject getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String sqlJson = rs.getString(columnName);
        if (null != sqlJson) {
            return JSONObject.parseObject(sqlJson);
        }
        return null;
    }

    //根据列索引，获取可以为空的结果
    @Override
    public JSONObject getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String sqlJson = rs.getString(columnIndex);
        if (null != sqlJson) {
            return JSONObject.parseObject(sqlJson);
        }
        return null;
    }

    @Override
    public JSONObject getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String sqlJson = cs.getString(columnIndex);
        if (null != sqlJson) {
            return JSONObject.parseObject(sqlJson);
        }
        return null;
    }
}

