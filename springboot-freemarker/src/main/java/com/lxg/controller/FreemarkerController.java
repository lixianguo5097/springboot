package com.lxg.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @author XIANGUO LI
 * @date 2019-12-3 17:56
 */
@Controller
public class FreemarkerController {
    /**
     * 这里不能自己new否则会认为 是空，报错
     * @param dataModel
     * @return
     */
    @RequestMapping("/freemarkerIndex")
    public String freemarker(Map<String, Object> dataModel) {
        dataModel.put("name", "张三");
        dataModel.put("message", "hello world");

        List<Map<String,Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "苹果");
        map1.put("price", 4.5);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "香蕉");
        map2.put("price", 6.3);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("name", "橘子");
        map3.put("price", 5.2);
        list.add(map1);
        list.add(map2);
        list.add(map3);

        dataModel.put("goodsList", list);
        dataModel.put("today", new Date());
        dataModel.put("number", 123456789L);
        return "test";
    }


    @Test
    public void createFileByFreemarker() throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setClassForTemplateLoading(this.getClass(), "/templates");
        Template template = configuration.getTemplate("test.ftl");

        Map<String, Object> dataModel  =new HashMap<>();
        dataModel.put("name", "张三");
        dataModel.put("message", "hello world");

        List<Map<String,Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "苹果");
        map1.put("price", 4.5);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "香蕉");
        map2.put("price", 6.3);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("name", "橘子");
        map3.put("price", 5.2);
        list.add(map1);
        list.add(map2);
        list.add(map3);

        dataModel.put("goodsList", list);
        dataModel.put("today", new Date());
        dataModel.put("number", 123456789L);
        FileWriter fileWriter = new FileWriter("D:/test.html");
        template.process(dataModel, fileWriter);
        fileWriter.close();
    }

}
