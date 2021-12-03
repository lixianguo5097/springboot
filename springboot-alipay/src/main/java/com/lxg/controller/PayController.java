package com.lxg.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.lxg.config.AliPayConfig;
import com.lxg.entity.Order;
import com.lxg.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author LXG
 * @date 2021-12-2
 */
@Controller
@RequestMapping
public class PayController {
    @Autowired
    private PayService payService;

    @RequestMapping
    public String index() {
        return "index";
    }

    /**
     * 电脑的支付
     * @param order
     * @return
     */
    @RequestMapping("/pay")
    @ResponseBody
    public String pay(@RequestBody Order order) {
        long orderId = System.currentTimeMillis();
        order.setOrderId(orderId);
        System.out.println(orderId);
        return payService.pay(order);
    }

    /**
     * 查询订单支付状态
     * @return
     */
    @RequestMapping("/search/status/{id}")
    @ResponseBody
    public String searchOrderStatus(@PathVariable("id") String id) throws AlipayApiException {
        return payService.searchOrderStatus(id);
    }


}
