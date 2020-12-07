package com.lxg.common.utils;

import com.lxg.common.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @description: 拷贝
 * @author cxm
 * @date 2019/10/9 16:48
 */
@Slf4j
public class BeanCopyUtils {

    private BeanCopyUtils(){

    }

    /**
     *  将一个实体list copy到另一个实体list
     * @param sourceList 源list
     * @param targetList 目标list
     * @param targetEntityClass 目标实体 class
     * @param <T>
     */
    public static<T> void copyList(List sourceList, List<T> targetList, Class<T> targetEntityClass) {

        if ((!Objects.isNull(sourceList)) && (!Objects.isNull(targetList))) {

            sourceList.forEach(item -> {
                try {
                    T data = targetEntityClass.newInstance();
                    BeanUtils.copyProperties(item, data);
                    targetList.add(data);
                } catch (Exception e) {
                    log.error("copyList出错",e);
                    throw MyException.newException("copyList出错");
                }
            });
        }
    }

    /**
     * 将一个实体list copy到另一个实体list
     * @param sourceList 源list
     * @param targetEntityClass 目标实体 class
     * @param <T>
     */
    public static<T> List<T> copyList(List sourceList,  Class<T> targetEntityClass) {

        if (sourceList.isEmpty()) {
            return null;
        }

        List<T> targetList = new ArrayList<>(sourceList.size());
        copyList(sourceList,targetList,targetEntityClass);
        return targetList;

    }

    /**
     *
     * 将一个实体的字段copy到另一个实体
     * @param sourceEntity 源实体
     * @param targetClass 目标实体
     * @return
     */
    public static <T> T copyBean(Object sourceEntity, Class<T> targetClass) {
        // 判断originEntity是否为空!
        if (sourceEntity == null) {
            return null;
        }
        // 判断targetClass 是否为空
        if (targetClass == null) {
            return null;
        }
        try {
            T newInstance = targetClass.newInstance();
            BeanUtils.copyProperties(sourceEntity, newInstance);
            return newInstance;

        } catch (Exception e) {
            log.error("copy实体出错",e);
            return null;
        }
    }
}