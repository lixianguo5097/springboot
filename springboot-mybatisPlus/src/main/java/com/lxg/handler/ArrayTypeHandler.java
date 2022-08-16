package com.lxg.handler;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  存储到数据库, 将String数组转换成字符串;
 *  从数据库获取数据, 将字符串转为LONG数组.
 */
@MappedTypes({String[].class})
@MappedJdbcTypes({JdbcType.VARCHAR})
public class ArrayTypeHandler extends BaseTypeHandler<Object[]> {

    private static Object[] objArr = new Object[]{};

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
                                    Object[] parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSON.toJSONString(parameter));
    }

    @Override
    public Object[] getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        return JSON.parseArray(rs.getString(columnName)).toArray(objArr);
    }

    @Override
    public Object[] getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        return JSON.parseArray(rs.getString(columnIndex)).toArray(objArr);
    }

    @Override
    public Object[] getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        return JSON.parseArray(cs.getString(columnIndex)).toArray(objArr);
    }

}
