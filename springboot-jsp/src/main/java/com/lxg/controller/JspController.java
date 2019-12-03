package com.lxg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author XIANGUO LI
 * @date 2019-12-3 18:17
 */
@Controller
public class JspController {
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
