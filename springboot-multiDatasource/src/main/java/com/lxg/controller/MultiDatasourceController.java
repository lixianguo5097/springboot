package com.lxg.controller;

import com.lxg.member.MemberMapper;
import com.lxg.order.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiDatasourceController {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 调用member数据库
     * @param name
     * @param age
     * @return
     */
    @RequestMapping("/addUser")
    public String addUser(String name, Integer age) {
        return memberMapper.addUser(name, age)>0?"success":"fail";
    }

    /**
     * 调用order数据库
     * @param number
     * @return
     */
    @RequestMapping("/addOrder")
    public String addOrder(String number) {
        return orderMapper.addOrder(number)>0?"success":"fail";
    }

}
