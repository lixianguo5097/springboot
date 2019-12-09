package com.lxg.controller;

import com.lxg.config.member.MemberDatasourceConfig;
import com.lxg.member.MemberMapper;
import com.lxg.order.OrderMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * @author XIANGUO LI
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
    @Transactional(rollbackFor = Exception.class,transactionManager = "memberTransactionManager")
    @RequestMapping("/addUser")
    public String addUser(String name, Integer age)  {
        int i = memberMapper.addUser(name, age);
        int j = 10 / 0;
        System.out.println(j);
        return i>0?"success":"fail";
    }

    /**
     * 调用order数据库
     * @param number
     * @return
     */
    @Transactional(rollbackFor = Exception.class,transactionManager = "memberTransactionManager")
    @RequestMapping("/addOrder")
    public String addOrder(Integer number) {
        return orderMapper.addOrder(number)>0?"success":"fail";
    }

}
