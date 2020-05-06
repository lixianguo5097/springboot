package com.lxg.controller.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.lxg.listener.EasyUserListener;
import com.lxg.model.easyexcel.EasyUser;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LXG
 * @date 2020-5-6
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/poi/upload")
public class EasyExcelUploadController {
    /**
     简单的读取excel文件
     */
    @Test
    public void read() {
        String fileName = "D:/1588743458504.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 参数一：读取的excel文件路径
        // 参数二：读取sheet的一行，将参数封装在DemoData实体类中
        // 参数三：读取每一行的时候会执行DemoDataListener监听器
        EasyExcel.read(fileName, EasyUser.class, new EasyUserListener()).sheet().doRead();
    }

}
