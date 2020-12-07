package com.lxg.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;


/**
 * @Description Json、Map、实体类--互转的工具类
 * @ClassName JsonMapUtils
 * @Author ph
 * @Date 19:55 2018/11/5
 * @Version 1.0
 */
public class JsonMapUtils {
    /**
     * @return java.lang.String
     * @Description 可以把实体类、map，转为json字符串
     * @Author ph
     * @Date 19:58 2018/11/5
     * @Param [obj]
     */
    public static String obj2json(Object obj) throws Exception {
        return JSON.toJSONString(obj);
    }

    /**
     * @return T
     * @Description Json转对象
     * @Author ph
     * @Date 19:58 2018/11/5
     * @Param [jsonStr, clazz]
     */
    public static <T> T json2obj(String jsonStr, Class<T> clazz) throws Exception {
        return JSON.parseObject(jsonStr, clazz);
    }

    /**
     * @return java.util.Map<java.lang.String       ,       java.lang.Object>
     * @Description Json转Map
     * @Author ph
     * @Date 19:58 2018/11/5
     * @Param [jsonStr]
     */
    @SuppressWarnings("unchecked")
    public static <T> Map<String, Object> json2map(String jsonStr) throws Exception {
        return JSON.parseObject(jsonStr, Map.class);
    }

    /**
     * @return T
     * @Description Map转对象
     * @Author ph
     * @Date 19:59 2018/11/5
     * @Param [map, clazz]
     */
    public static <T> T map2obj(Map<?, ?> map, Class<T> clazz) throws Exception {
        return JSON.parseObject(JSON.toJSONString(map), clazz);
    }


    /**
     * json转list
     *
     * @param jsonStr
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> json2list(String jsonStr) throws Exception {
        return JSON.parseObject(jsonStr, new TypeReference<List<T>>() {
        });
    }
}
