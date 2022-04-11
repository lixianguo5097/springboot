package com.lxg.controller;

import com.google.common.util.concurrent.RateLimiter;
import com.lxg.config.Limit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author LXG
 * @date 2022-4-11
 */
@Slf4j
@RestController
@RequestMapping("/limit")
public class LimitController {
    @GetMapping("/1")
    @Limit(key = "limit1", permitsPerSecond = 1, timeout = 500, timeunit = TimeUnit.MILLISECONDS,msg = "当前排队人数较多，请稍后再试！")
    public String limit1() {
        log.info("令牌桶limit1获取令牌成功");
        return "ok";
    }


    @GetMapping("/2")
    @Limit(key = "limit2", permitsPerSecond = 2, timeout = 500, timeunit = TimeUnit.MILLISECONDS,msg = "系统繁忙，请稍后再试！")
    public String limit2() {
        log.info("令牌桶limit2获取令牌成功");
        return "ok";
    }
}