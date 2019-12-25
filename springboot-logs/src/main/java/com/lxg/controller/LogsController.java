package com.lxg.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LXG
 * @date 2019-12-10
 */
@RestController
@Slf4j
public class LogsController {
    @Value("${lxg.msg}")
    private String msg;

    @RequestMapping("/log")
    public void getLog() {
        try {
            log.error("错误日志输出");
            log.info("信息日志输出");
            int i = 10 / 0;
        } catch (Exception e) {
            log.error("Exception", e);
        }
    }

    @RequestMapping("/msg")
    public String getMsg() {
        return msg;
    }
}
