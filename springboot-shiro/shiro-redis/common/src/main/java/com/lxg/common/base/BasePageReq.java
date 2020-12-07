package com.lxg.common.base;

import com.baomidou.mybatisplus.plugins.Page;
import lombok.Data;

/**
 * @ClassName BasePageReq
 * @Author ph
 * @Version 1.0
 * @Description 分页参数
 * @Date 2020/4/7 16:58
 */
@Data
public class BasePageReq {

    public BasePageReq(){
    }
    /**
     * 页码（默认1页）
     */
    private Integer current = 1;
    /**
     * 长度（默认长度10）
     */
    private Integer size = 10;

    public Page getPage(){
        return new Page(current, size);
    }

    /**
     * 排序字段
     * */
    private String field;

    /**
     * 排序方式  asc/desc
     * */
    private String order;
}