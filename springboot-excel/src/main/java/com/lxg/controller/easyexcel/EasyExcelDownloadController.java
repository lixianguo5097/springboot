package com.lxg.controller.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.lxg.model.easyexcel.EasyUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * 改为浏览器附件下载，只需要去除filePath
 * 添加
 * response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
 * Excel.write(reponse.getOutgetOutputStream())而不是filePath
 * @author LXG
 * @date 2020-4-30
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/easyexcel/download")
public class EasyExcelDownloadController {
    /**
     * 写一个简单的excel
     */
    @GetMapping("/excelSimpleLocal")
    public String excelSimpleLocal() {
        String filePath = "D:/easy_user_simple.xlsx";
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可 EasyExcel.write(fileName, User.class).excelType
        // 去除标题行EasyExcel.write(filePath, EasyUser.class).needHead(false)
        // 参数一：写入excel文件路径
        // 参数二：写入的数据类型是EasyUser
        // doWrite()方法是写入的数据，结果是List<EasyUser>集合
        // EasyExcel的样式是在对应实体类中加注解实现的
        EasyExcel.write(filePath, EasyUser.class).sheet("user工作表").doWrite(getEasyUsers());
        return "download success";
    }

    @GetMapping("/excelSimpleBrowser")
    public void excelSimpleBrowser(HttpServletResponse response) {
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可 EasyExcel.write(fileName, User.class).excelType
        // 去除标题行EasyExcel.write(filePath, EasyUser.class).needHead(false)
        // 参数一：写入excel文件路径
        // 参数二：写入的数据类型是EasyUser
        // doWrite()方法是写入的数据，结果是List<EasyUser>集合
        // EasyExcel的样式是在对应实体类中加注解实现的
        try {
            String fileName = URLEncoder.encode("easy_user_simple_browser", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), EasyUser.class).sheet("user工作表").doWrite(getEasyUsers());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出指定字段，或者忽略指定字段
     */
    @GetMapping("/excludeOrIncludeWrite")
    public String excludeOrIncludeWrite() {
        String filePath = "D:\\excludeWrite.xlsx";

        // 忽略 age字段 不导出
        Set<String> excludeColumnFiledNames = new HashSet<String>();
        excludeColumnFiledNames.add("age");
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(filePath, EasyUser.class).excludeColumnFiledNames(excludeColumnFiledNames).sheet("忽略age")
                .doWrite(getEasyUsers());

        filePath = "D:\\includeWrite.xlsx";
        // 根据用户传入字段 假设我们只要导出 age字段
        Set<String> includeColumnFiledNames = new HashSet<String>();
        includeColumnFiledNames.add("age");
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(filePath, EasyUser.class).includeColumnFiledNames(includeColumnFiledNames).sheet("导出age")
                .doWrite(getEasyUsers());
        return "download success";
    }

    @GetMapping("/excludeWriteBrowser")
    public void excludeBrowser(HttpServletResponse response) {
        // 忽略 age字段 不导出
        Set<String> excludeColumnFiledNames = new HashSet<String>();
        excludeColumnFiledNames.add("age");
        try {
            String fileName = URLEncoder.encode("easy_user_exclude_browser", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), EasyUser.class).excludeColumnFiledNames(excludeColumnFiledNames).sheet("忽略age")
                    .doWrite(getEasyUsers());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/includeWriteBrowser")
    public void includeWriteBrowser(HttpServletResponse response) {
        // 只导出age字段
        Set<String> includeColumnFiledNames = new HashSet<String>();
        includeColumnFiledNames.add("age");
        try {
            String fileName = URLEncoder.encode("easy_user_include_browser", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), EasyUser.class).includeColumnFiledNames(includeColumnFiledNames).sheet("导出age")
                    .doWrite(getEasyUsers());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<EasyUser> getEasyUsers() {
        List<EasyUser> users = new ArrayList<>();
        Collections.addAll(users,
                new EasyUser("20180030", "张三", 18, "男"),
                new EasyUser("20180031", "李四", 17, "女"),
                new EasyUser("20180032", "王五", 16, "女"),
                new EasyUser("20180033", "赵六", 15, "男"),
                new EasyUser("20180034", "田七", 19, "女")
        );
        return users;
    }
}
