package com.lxg.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author lxg
 * @date 2020/4/8 16:43
 * @description
 */
@Slf4j
@Component
public class UserConsumer {

    @KafkaListener(topics = {"${spring.kafka.topic.userTopic}"})
    public void userConsumer(String message) {
        log.info("receive msg " + message);
    }
}
