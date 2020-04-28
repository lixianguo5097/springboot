package com.lxg.controller;

import com.lxg.model.Data;
import com.lxg.model.ExcelModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 会发现POI合并单元格比较麻烦，而且不好设置样式，使用阿里出品的EasyExcel会方便许多
 * @author LXG
 * @date 2020-4-28
 */
@RestController
@RequestMapping("/download")
public class PoiDownloadMergeCellController {

    @GetMapping("/downloadMergeCell")
    public void downloadMergeCell(HttpServletResponse response) {
        try {
            HSSFWorkbook workbook = ExcelModel.createExcel(getData());
            //浏览器下载附件
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("data.xls", "UTF-8"));
            workbook.write(response.getOutputStream());

            //下载到本地
            FileOutputStream out = new FileOutputStream(new File("D:/data.xls"));
            workbook.write(out);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Data> getData() {
        List<Data> dataList = new ArrayList<>();
        Collections.addAll(dataList,
                new Data(new Date(),"南海海洋站","0546",11.3,2.32,4.3,2.34,5.3,6.47,33.8,3.9,29.32,94.5,93.6,29.4,19.56,9.0),
                new Data(new Date(),"南海海洋站","0546",11.3,2.32,4.3,2.34,5.3,6.47,33.8,3.9,29.32,94.5,93.6,29.4,19.56,9.0),
                new Data(new Date(),"南海海洋站","0546",11.3,2.32,4.3,2.34,5.3,6.47,33.8,3.9,29.32,94.5,93.6,29.4,19.56,9.0),
                new Data(new Date(),"南海海洋站","0546",11.3,2.32,4.3,2.34,5.3,6.47,33.8,3.9,29.32,94.5,93.6,29.4,19.56,9.0),
                new Data(new Date(),"南海海洋站","0546",11.3,2.32,4.3,2.34,5.3,6.47,33.8,3.9,29.32,94.5,93.6,29.4,19.56,9.0),
                new Data(new Date(),"南海海洋站","0546",11.3,2.32,4.3,2.34,5.3,6.47,33.8,3.9,29.32,94.5,93.6,29.4,19.56,9.0),
                new Data(new Date(),"南海海洋站","0546",11.3,2.32,4.3,2.34,5.3,6.47,33.8,3.9,29.32,94.5,93.6,29.4,19.56,9.0),
                new Data(new Date(),"南海海洋站","0546",11.3,2.32,4.3,2.34,5.3,6.47,33.8,3.9,29.32,94.5,93.6,29.4,19.56,9.0),
                new Data(new Date(),"南海海洋站","0546",11.3,2.32,4.3,2.34,5.3,6.47,33.8,3.9,29.32,94.5,93.6,29.4,19.56,9.0),
                new Data(new Date(),"南海海洋站","0546",11.3,2.32,4.3,2.34,5.3,6.47,33.8,3.9,29.32,94.5,93.6,29.4,19.56,9.0),
                new Data(new Date(),"南海海洋站","0546",11.3,2.32,4.3,2.34,5.3,6.47,33.8,3.9,29.32,94.5,93.6,29.4,19.56,9.0),
                new Data(new Date(),"南海海洋站","0546",11.3,2.32,4.3,2.34,5.3,6.47,33.8,3.9,29.32,94.5,93.6,29.4,19.56,9.0)
        );
        return dataList;
    }
}
