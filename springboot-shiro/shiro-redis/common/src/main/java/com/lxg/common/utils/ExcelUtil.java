package com.lxg.common.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.lxg.common.exception.MyException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ExcelUtil
 * @Description
 * @Author ph
 * @Date 2020/4/28 14:12
 * @Version 1.0
 **/
@Slf4j
public class ExcelUtil {

    /**
     * * 根据实体类导出excel
     *
     * @param response
     * @param dataList 导出的数据
     * @param fileName excel文件名称
     * @param sheetName sheet名称
     * @param <T> 实体类名称
     */
    public static <T> void exportExcel(HttpServletResponse response, List<T> dataList, String fileName
            , String sheetName, T t) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 防止中文乱码
            String excelName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + excelName + ".xlsx");
            ExcelWriterBuilder workBook = EasyExcel.write(response.getOutputStream(),t.getClass());

            ExcelWriterSheetBuilder sheet = workBook.sheet(sheetName);
            sheet.doWrite(dataList);
        } catch (IOException e) {
            log.info("导出excel出错");
            throw MyException.newException("系统错误,请联系管理员");
        }
    }

    /**
     * 根据模板导出excel
     *
     * @param response
     * @param templateUrl 模板地址
     * @param data 填充的内容
     * @param map 另补充的字段信息
     */
    public static void  complexFill(HttpServletResponse response,String templateUrl,
                                    Object data,Map<String, Object> map) {
        try {
            //xls格式
            //response.setContentType("application/vnd.ms-excel");
            //xlsx格式
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 防止中文乱码
            String excelName = URLEncoder.encode("汕尾海洋观测站数据质控表"+new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date()), "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + excelName + ".xlsx");

            // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
            // {} 代表普通变量 {.} 代表是list的变量
            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).withTemplate(templateUrl).build();
            WriteSheet writeSheet = EasyExcel.writerSheet().build();
            // 这里注意 入参用了forceNewRow 代表在写入list的时候不管list下面有没有空行 都会创建一行，然后下面的数据往后移动。默认 是false，会直接使用下一行，如果没有则创建。
            // forceNewRow 如果设置了true,有个缺点 就是他会把所有的数据都放到内存了，所以慎用
            // 简单的说 如果你的模板有list,且list不是最后一行，下面还有数据需要填充 就必须设置 forceNewRow=true 但是这个就会把所有数据放到内存 会很耗内存
            // 如果数据量大 list不是最后一行 参照下一个
            FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
            excelWriter.fill(data, fillConfig, writeSheet);
            //需要另外填充单个变量
            excelWriter.fill(map, writeSheet);
            excelWriter.finish();
        } catch (IOException e) {
            log.info("导出excel出错");
            throw MyException.newException("系统错误,请联系管理员");
        }
    }

}
