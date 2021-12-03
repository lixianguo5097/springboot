package com.lxg.entity;

import lombok.Data;

/**
 * @author LXG
 * @date 2021-12-2
 */
@Data
public class Order {
    /**
     * 订单编号
     */
    private Long orderId;
    /**
     * 金额
     */
    private Double price;
    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String describe;
}
