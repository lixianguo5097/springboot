package com.lxg.common.base;

import lombok.Data;

/**
 * @ClassName BasePageResp
 * @Author ph
 * @Version 1.0
 * @Description
 * @Date 2020/4/7 17:06
 */
@Data
public class BasePageResp {

    private Integer total;
    private Integer size;
    private Integer pages;
    private Integer current;

}
