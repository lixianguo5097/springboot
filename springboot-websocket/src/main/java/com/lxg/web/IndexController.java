package com.lxg.web;

import com.lxg.config.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author LXG
 * @date 2021-12-2
 */
@Controller
@RequestMapping
public class IndexController {

    @Autowired
    private WebSocket webSocket;

    /**
     * 跳转index页面
     * @return
     */
    @RequestMapping
    public String index() {
        return "index";
    }

    /**
     * 向html发送webSocket消息
     */
    @ResponseBody
    @RequestMapping("/msg")
    public void sendMessage(String msg) {
        webSocket.sendMessage(msg);
    }
}
