package com.lxg.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.lxg.entity.Order;
import org.springframework.stereotype.Service;

/**
 * @author LXG
 * @date 2021-12-2
 */
public interface PayService {
    /**
     * 支付接口
     * @param order
     * @return
     */
    String pay(Order order);

    /**
     * 查询订单状态
     * @param id
     * @return
     */
    String searchOrderStatus(String id) throws AlipayApiException;
}
