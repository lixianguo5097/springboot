package com.lxg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author XIANGUO LI
 * @date 2019-12-3 17:56
 */
@Controller
public class FreemarkerController {
    @RequestMapping("/freemarkerIndex")
    public String freemarker(Map<String, Object> result) {
        result.put("name", "yushengjun");
        result.put("sex", "0");
        List<String> listResult = new ArrayList<String>();
        listResult.add("zhangsan");
        listResult.add("lisi");
        listResult.add("itmayiedu");
        result.put("userlist", listResult);
        return "index";
    }
}
