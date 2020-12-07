package com.lxg.common.config;



import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * 自定义填充公共字段
 * @author LiuLP
 * @Date 2018-08-03 10:12
 * @Description
 */

public class MyMetaObjectHandler extends MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Date date = new Date();
        Object createTime = getFieldValByName("createTime", metaObject);
        if (createTime == null) {
            setFieldValByName("createTime", date, metaObject);
        }

        Object updateTime = getFieldValByName("updateTime", metaObject);
        if (updateTime == null) {
            setFieldValByName("updateTime", date, metaObject);
        }

        Object delFlag = getFieldValByName("delFlag", metaObject);
        if (delFlag == null) {
            setFieldValByName("delFlag", false, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        setFieldValByName("updateTime", new Date(), metaObject);
    }
}