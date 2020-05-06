package com.lxg.controller.poi;

import com.lxg.constant.ExcelConstant;
import com.lxg.model.poi.User;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * EXCEL的行数、单元格都是从索引0开始的
 *
 * HSSFWorkbook:是操作Excel2003以前（包括2003）的版本，扩展名是.xls；
 * XSSFWorkbook:是操作Excel2007后的版本，扩展名是.xlsx
 *
 * 遇到的问题及解决方案：
 * 当需要在body内携带参数以Post方式请求时例如导出数据库中某段日期内性别为男的学生名单，
 * 可以先将excel生成在静态服务器例如nginx配置的某个位置，然后通过HTTP请求下载下来
 *
 * 如果是Get方式请求，拼接url即可
 * 
 * 更改为浏览器附件下载，只需要在最后加上：
 *  response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("student.xls", "UTF-8"));
 *  workbook.write(response.getOutputStream());
 *  
 *  同时去掉FileOutputStream
 * 
 * @author LXG
 * @date 2020-4-24
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/poi/download")
public class PoiDownloadController {


    @GetMapping("/excelSimpleLocal")
    public String excelSimpleLocal() {
        try (FileOutputStream out = new FileOutputStream("D:/user_simple.xls");) {
            //创建工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            //创建工作表
            HSSFSheet sheet = workbook.createSheet("用户表");

            //设置列宽
            sheet.setDefaultColumnWidth(20);
            //设置行高
            sheet.setDefaultRowHeight((short) 350);

            //第一行 标题行
            HSSFRow rowHead = sheet.createRow(0);
            rowHead.createCell(0).setCellValue("学号");
            rowHead.createCell(1).setCellValue("姓名");
            rowHead.createCell(2).setCellValue("年龄");

            //内容行 第一行
            HSSFRow rowBodyOne = sheet.createRow(1);
            //第一行的第一个单元格
            rowBodyOne.createCell(0).setCellValue("2020427");
            //第一行的第二个单元格
            rowBodyOne.createCell(1).setCellValue("张三");
            //第一行的第三个单元格
            rowBodyOne.createCell(2).setCellValue(22);
            //第一行的第四个单元格
            rowBodyOne.createCell(3).setCellValue("男");

            //内容行 第二行
            HSSFRow rowBodyTwo = sheet.createRow(2);
            //第二行的第一个单元格
            rowBodyTwo.createCell(0).setCellValue("2020428");
            //第二行的第二个单元格
            rowBodyTwo.createCell(1).setCellValue("李四");
            //第二行的第三个单元格
            rowBodyTwo.createCell(2).setCellValue(21);
            //第二行的第四个单元格
            rowBodyTwo.createCell(3).setCellValue("女");

            //输出内容
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "download success";
    }

    /**
     * 假装从数据库获取的数据
     */
    @GetMapping("/excelFromDBLocal")
    public String excelFromDB() {
        try (FileOutputStream out = new FileOutputStream("D:/user_fromdb.xls")) {
            //创建工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            //创建工作表
            HSSFSheet sheet = workbook.createSheet("用户表");

            //设置列宽
            sheet.setDefaultColumnWidth(20);
            //设置行高
            sheet.setDefaultRowHeight((short) 350);

            HSSFRow rowHead = sheet.createRow(0);
            rowHead.createCell(0).setCellValue("学号");
            rowHead.createCell(1).setCellValue("姓名");
            rowHead.createCell(2).setCellValue("年龄");
            rowHead.createCell(3).setCellValue("性别");

            //假装从数据库获取的数据
            List<User> users = getUsers();
            for (int i = 0; i < users.size(); i++) {
                //要从做索引1开始，索引0已经作为标题行
                HSSFRow row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(users.get(i).getStudentId());
                row.createCell(1).setCellValue(users.get(i).getName());
                row.createCell(2).setCellValue(users.get(i).getAge());
                row.createCell(3).setCellValue(users.get(i).getSex());

            }
            //输出内容
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "download success";
    }

    /**
     * 反射获取字段 并且设置样式
     */
    @GetMapping("/excelByReflexLocal")
    public String excelByReflex() {
        try (FileOutputStream out = new FileOutputStream("D:/user_reflex.xls")) {
            //创建工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            //创建工作表
            HSSFSheet sheet = workbook.createSheet("用户表");

            //设置列宽
            sheet.setDefaultColumnWidth(20);
            //设置行高
            sheet.setDefaultRowHeight((short) 350);

            //为单元格内的值做数据校验，性别一栏只能填写男或女，年龄一栏的值只能在0到100之间
            //设置该规则的单元格范围,锁定性别一列
            CellRangeAddressList dstAddressList = new CellRangeAddressList(0, 10, 3, 3);
            //设置下拉列表的内容
            String[] textList = { "男", "女" };
            DataValidationHelper helper = sheet.getDataValidationHelper();
            DataValidation dstDataValidation = helper.createValidation(helper.createExplicitListConstraint(textList),
                    dstAddressList);
            //鼠标移动到单元格上显示的内容
            dstDataValidation.createPromptBox("提示", "请输入正确性别：男/女");
            dstDataValidation.setShowErrorBox(true);
            dstDataValidation.setShowPromptBox(true);
            dstDataValidation.setEmptyCellAllowed(false);
            sheet.addValidationData(dstDataValidation);

            //设置该规则的单元格范围,锁定年龄一列
            CellRangeAddressList dstAddressList2 = new CellRangeAddressList(0, 500, 2, 2);
            //设置取值范围
            DataValidationConstraint dvc = helper.createNumericConstraint(DVConstraint.ValidationType.INTEGER,
                    DVConstraint.OperatorType.BETWEEN, "0", "100");
            //错误提示
            DataValidation dstDataValidation2 = helper.createValidation(dvc, dstAddressList2);
            dstDataValidation2.createErrorBox("年龄错误！", "请输入正确年龄：0-100");
            dstDataValidation2.setEmptyCellAllowed(false);
            dstDataValidation2.setShowErrorBox(true);
            sheet.addValidationData(dstDataValidation2);


            //创建标题行，通过反射，将常量的值按顺序填入单元格
            HSSFRow rowHead = sheet.createRow(0);

            //获取所有字段
            Field[] excelFields = ExcelConstant.class.getDeclaredFields();
            for (int i = 0; i < excelFields.length; i++) {
                Field excelField = excelFields[i];
                //获取私有属性
                excelField.setAccessible(true);
                Object value = excelField.get(ExcelConstant.class);
                //为每个单元格的值做类型判断与转换，以及设置样式
                changeType(workbook, rowHead, i, value);

            }

            //创建内容行，通过反射，将实体类字段的值按顺序填入单元格
            List<User> users = getUsers();
            for (int i = 0; i < users.size(); i++) {
                HSSFRow rows = sheet.createRow(i + 1);
                //获取所有字段 包括私有
                Field[] fields = users.get(i).getClass().getDeclaredFields();
                //遍历所有字段
                for (int j = 0; j < fields.length; j++) {
                    //获取私有属性
                    fields[j].setAccessible(true);
                    //获取的值写入到单元格中
                    //获取字段的值
                    Object value = fields[j].get(users.get(i));
                    //为每个单元格的值做类型判断与转换，以及设置样式
                    changeType(workbook, rows, j, value);
                }
            }
            //输出内容
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "download success";
    }


    private void changeType(HSSFWorkbook workbook, HSSFRow rows, int j, Object value) {
        //创建单元格样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        //水平居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //垂直居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        //创建单元格
        HSSFCell cell = rows.createCell(j);
        //为单元格设置样式
        cell.setCellStyle(cellStyle);

        //判断单元格中的值的类型并设置值
        if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Date) {
            //这里可以对日期格式进行转换
            cell.setCellValue((Date) value);
        } else if (value instanceof Float) {
            cell.setCellValue((Float) value);
        }
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        Collections.addAll(users,
                new User("20180030", "张三", 18, "男"),
                new User("20180031", "李四", 17, "女"),
                new User("20180032", "王五", 16, "女"),
                new User("20180033", "赵六", 15, "男"),
                new User("20180034", "田七", 19, "女")
        );
        return users;
    }


    /**
     * 反射获取字段 并且设置样式
     * 以上是直接生成到本地，接下来作为附件，浏览器访问接口下载excel的形式
     */
    @GetMapping("/excelByReflexBrowser")
    public void excelByReflexDownload(HttpServletResponse response) {
        try  {
            //创建工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            //创建工作表
            HSSFSheet sheet = workbook.createSheet("用户表");

            //设置列宽
            sheet.setDefaultColumnWidth(20);
            //设置行高
            sheet.setDefaultRowHeight((short) 350);

            //为单元格内的值做数据校验，性别一栏只能填写男或女，年龄一栏的值只能在0到100之间
            //设置该规则的单元格范围,锁定性别一列
            CellRangeAddressList dstAddressList = new CellRangeAddressList(0, 10, 3, 3);
            //设置下拉列表的内容
            String[] textList = { "男", "女" };
            DataValidationHelper helper = sheet.getDataValidationHelper();
            DataValidation dstDataValidation = helper.createValidation(helper.createExplicitListConstraint(textList),
                    dstAddressList);
            //鼠标移动到单元格上显示的内容
            dstDataValidation.createPromptBox("提示", "请输入正确性别：男/女");
            dstDataValidation.setShowErrorBox(true);
            dstDataValidation.setShowPromptBox(true);
            dstDataValidation.setEmptyCellAllowed(false);
            sheet.addValidationData(dstDataValidation);

            //设置该规则的单元格范围,锁定年龄一列
            CellRangeAddressList dstAddressList2 = new CellRangeAddressList(0, 500, 2, 2);
            //设置取值范围
            DataValidationConstraint dvc = helper.createNumericConstraint(DVConstraint.ValidationType.INTEGER,
                    DVConstraint.OperatorType.BETWEEN, "0", "100");
            //错误提示
            DataValidation dstDataValidation2 = helper.createValidation(dvc, dstAddressList2);
            dstDataValidation2.createErrorBox("年龄错误！", "请输入正确年龄：0-100");
            dstDataValidation2.setEmptyCellAllowed(false);
            dstDataValidation2.setShowErrorBox(true);
            sheet.addValidationData(dstDataValidation2);


            //创建标题行，通过反射，将常量的值按顺序填入单元格
            HSSFRow rowHead = sheet.createRow(0);

            //获取所有字段
            Field[] excelFields = ExcelConstant.class.getDeclaredFields();
            for (int i = 0; i < excelFields.length; i++) {
                Field excelField = excelFields[i];
                //获取私有属性
                excelField.setAccessible(true);
                Object value = excelField.get(ExcelConstant.class);
                //为每个单元格的值做类型判断与转换，以及设置样式
                changeType(workbook, rowHead, i, value);

            }

            //创建内容行，通过反射，将实体类字段的值按顺序填入单元格
            List<User> users = getUsers();
            for (int i = 0; i < users.size(); i++) {
                HSSFRow rows = sheet.createRow(i + 1);
                //获取所有字段 包括私有
                Field[] fields = users.get(i).getClass().getDeclaredFields();
                //遍历所有字段
                for (int j = 0; j < fields.length; j++) {
                    //获取私有属性
                    fields[j].setAccessible(true);
                    //获取的值写入到单元格中
                    //获取字段的值
                    Object value = fields[j].get(users.get(i));
                    //为每个单元格的值做类型判断与转换，以及设置样式
                    changeType(workbook, rows, j, value);
                }
            }

            //输出内容
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("user_download.xls", "UTF-8"));
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
