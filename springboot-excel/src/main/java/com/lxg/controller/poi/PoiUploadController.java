package com.lxg.controller.poi;

import com.lxg.model.poi.User;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel导入 这里是先走excel输出的接口，导出一个excel文件再做入库
 * @author LXG
 * @date 2020-4-27
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/poi/upload")
public class PoiUploadController {

    /**
     * 直接读本地文件
     */
    @GetMapping("/localExcel")
    public List<User> localExcel() {
        List<User> userList = new ArrayList<>();
        try {
            File uploadFile = new File("D:\\user_reflex.xls");
            FileInputStream inFile = new FileInputStream(uploadFile);
            HSSFWorkbook workbook = new HSSFWorkbook(inFile);
            //获取工作表数量
            int numberOfSheets = workbook.getNumberOfSheets();
            if (numberOfSheets > 0) {
                //读取所有的sheet 也可以只读一个sheet
                for (int i = 0; i < numberOfSheets; i++) {
                    HSSFSheet sheet = workbook.getSheetAt(i);
                    //总行数
                    int rows = sheet.getLastRowNum() ;
                    //从第二行开始遍历，因为第一行是标题行
                    for (int j = 1; j < rows+1; j++) {
                        User user = new User();
                        //第一个单元格--学号
                        HSSFCell cellId = sheet.getRow(j).getCell(0);
                        user.setStudentId(cellId.getStringCellValue());
                        //第二个单元格--姓名
                        HSSFCell cellName = sheet.getRow(j).getCell(1);
                        user.setName(cellName.getStringCellValue());
                        //第三个单元格--年龄
                        HSSFCell cellAge = sheet.getRow(j).getCell(2);
                        double value = cellAge.getNumericCellValue();
                        //double转integer,再设置进去
                        user.setAge(Double.valueOf(value).intValue());

                        //第四个单元格--性别
                        HSSFCell cellSex = sheet.getRow(j).getCell(3);
                        user.setSex(cellSex.getStringCellValue());

                        //添加到list,方便查看,可以在这里做入库操作
                        userList.add(user);
                    }
                    System.out.println(userList);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }

    /**
     * 读上传的文件
     */
    @PostMapping("/uploadExcel")
    public String uploadExcel(@RequestParam("file") MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            //获取工作表数量
            int numberOfSheets = workbook.getNumberOfSheets();
            if (numberOfSheets > 0) {
                //读取所有的sheet 也可以只读一个sheet 就直接workbook.getSheetAt(i)即可
                for (int i = 0; i < numberOfSheets; i++) {
                    List<User> userList = new ArrayList<>();
                    HSSFSheet sheet = workbook.getSheetAt(i);
                    //总行数
                    int rows = sheet.getLastRowNum() ;
                    //从第二行开始遍历，因为第一行是标题行
                    for (int j = 1; j < rows+1; j++) {
                        User user = new User();
                        //第一个单元格--学号
                        HSSFCell cellId = sheet.getRow(j).getCell(0);
                        user.setStudentId(cellId.getStringCellValue());
                        //第二个单元格--姓名
                        HSSFCell cellName = sheet.getRow(j).getCell(1);
                        user.setName(cellName.getStringCellValue());
                        //第三个单元格--年龄
                        HSSFCell cellAge = sheet.getRow(j).getCell(2);
                        double value = cellAge.getNumericCellValue();
                        //double转integer,再设置进去
                        user.setAge(Double.valueOf(value).intValue());

                        //第四个单元格--性别
                        HSSFCell cellSex = sheet.getRow(j).getCell(3);
                        user.setSex(cellSex.getStringCellValue());

                        //添加到list,方便查看,可以在这里做入库操作
                        userList.add(user);
                    }
                    System.out.println(userList);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传成功";
    }
}
