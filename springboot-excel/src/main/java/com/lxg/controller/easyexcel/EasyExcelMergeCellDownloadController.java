package com.lxg.controller.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.merge.LoopMergeStrategy;
import com.alibaba.excel.write.merge.OnceAbsoluteMergeStrategy;
import com.lxg.model.easyexcel.Fruit;
import com.lxg.strategy.MyMergeStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 如果要输出到服务器硬盘，只需要移除response.setHeader代码，
 * 再EasyExcel.write(response.getOutputStream())改为EasyExcel.write("服务器绝对路径")
 * @author LXG
 * @date 2020-4-30
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/easyexcel/download")
public class EasyExcelMergeCellDownloadController {
    @GetMapping("/loopMergeStrategy")
    public void loopMergeStrategy(HttpServletResponse response) {
        // 将第一列的数据每隔两行进行合并
        LoopMergeStrategy loopMergeStrategy = new LoopMergeStrategy(2, 0);
        try {
            String fileName = URLEncoder.encode("loopMergeStrategy", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), Fruit.class).registerWriteHandler(loopMergeStrategy)
                    .sheet("水果表").doWrite(getFruitData());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //指定坐标范围，对固定区间进行合并
    @GetMapping("/onceMergeStrategy")
    public void onceMergeStrategy(HttpServletResponse response) {
        try {
            OnceAbsoluteMergeStrategy onceAbsoluteMergeStrategy = new OnceAbsoluteMergeStrategy(1, 3, 1, 1);
            OnceAbsoluteMergeStrategy onceAbsoluteMergeStrategy2 = new OnceAbsoluteMergeStrategy(4, 5, 1, 1);

            String fileName = URLEncoder.encode("onceMergeStrategy", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), Fruit.class)
                    .registerWriteHandler(onceAbsoluteMergeStrategy)
                    .registerWriteHandler(onceAbsoluteMergeStrategy2)
                    .sheet("水果表").doWrite(getFruitData());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 在这个例子中，对于第1、4列，我们需要根据记录总数，都合并成一个单元格；
    // 对于第2列，需要根据每组的记录个数，分别进行单元格的合并；
    // 而对于第3列，则不要使用合并策略
    @GetMapping("/myMergeStrategy")
    public void myMergeStrategy(HttpServletResponse response) {
        try {
            MyMergeStrategy myMergeStrategy = new MyMergeStrategy(getFruitData(), getGroupData());
            String fileName = URLEncoder.encode("myMergeStrategy", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), Fruit.class).registerWriteHandler(myMergeStrategy)
                    .sheet("水果表").doWrite(getFruitData());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 模拟从数据库读取需要下载的列表信息
    private List<Fruit> getFruitData() {
        List<Fruit> returnList = new ArrayList<>();
        Collections.addAll(returnList,
                new Fruit("水果", "苹果", "红色", new Date()),
                new Fruit("水果", "苹果", "绿色", new Date()),
                new Fruit("水果", "苹果", "白色", new Date()),
                new Fruit("水果", "香蕉", "黄色", new Date()),
                new Fruit("水果", "香蕉", "青色", new Date())
        );
        return returnList;
    }

    private List<Integer> getGroupData() {
        List<Integer> list = new ArrayList<>();
        //苹果有三种
        list.add(3);
        //香蕉有两种
        list.add(2);
        return list;
    }
}
