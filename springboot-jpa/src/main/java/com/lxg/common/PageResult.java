package com.lxg.common;

import lombok.Data;

import java.util.List;

/**
 * 分页结果
 * @author LXG
 * @date 2019-8-30
 */
@Data
public class PageResult<T> {
    /**
     * 总记录数
     */
    private Long total;
    /**
     * 每页数据
     */
    private List<T> rows;
    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
