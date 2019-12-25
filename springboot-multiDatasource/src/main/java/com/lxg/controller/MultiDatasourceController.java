package com.lxg.controller;

import com.lxg.mapper.member.MemberMapper;
import com.lxg.mapper.order.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LXG
 */
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
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("/addUser")
    public String addUser(String name, Integer age)  {
        return memberMapper.addUser(name, age)>0?"success":"fail";
    }

    /**
     * 调用order数据库
     * @param number
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("/addOrder")
    public String addOrder(Integer number) {
        int i = 10 / number;
        return orderMapper.addOrder(number)>0?"success":"fail";
    }

    /**
     * 调用order和member数据库
     * @param name
     * @param age
     * @param number
     * @return
     */
    @Transactional(rollbackFor = Exception.class )
    @RequestMapping("/addOrderAndMember")
    public String addOrderAndMember(String name, Integer age,Integer number) {
        int i = memberMapper.addUser(name, age);
        int j = orderMapper.addOrder(number);
        int x = 10 / number;
        return j >0?"success":"fail";
    }
}
