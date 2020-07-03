package com.lxg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lxg.entity.User;
import com.lxg.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author lxg
 * @date 2020/4/8 16:28
 * @description
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Value("${spring.kafka.topic.userTopic}")
    private String userTopic;

    @Autowired
    KafkaTemplate kafkaTemplate;


    @Override
    public Boolean sendUserMsg() {
        User user = new User();
        user.setId(1);
        user.setUsername("lxg");
        user.setPassword("6767167");
        kafkaTemplate.send(userTopic, JSONObject.toJSONString(user));
        log.info("lxg");
        return Boolean.TRUE;
    }
}
