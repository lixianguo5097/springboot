package com.lxg.common;

import lombok.Data;

/**
 * @Description: 返回结果
 * @Author: XIANGUO LI
 * @Date: 2019-8-30 15:23
 */
@Data
public class Result {
    /**
     * 返回结构状态
     */
    private Boolean flag;

    /**
     * 返回状态码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回数据内容
     */
    private Object data;

    public Result(Boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(Boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result() {
    }
}
