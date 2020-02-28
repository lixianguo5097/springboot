package com.lxg.aop;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @Author LiuLP
 * @Date 2018-10-26 0026
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogAop {

    /**
     * 操作模块
     * @return
     */
    String operateModule() default "";

    /**
     * 操作内容
     * @return
     */
    String operation() default  "";
}
