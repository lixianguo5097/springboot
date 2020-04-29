package com.lxg.model;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 因为是从索引0开始，因此所有的行数、单元格都需要比excel表格内行号减一
 *
 * @author LXG
 * @date 2020-4-28
 */
public class ExcelModel {

    public static HSSFWorkbook createExcel(List<Data> dataList) {
        //创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //创建合并单元格对象
        //参数为起始行,结束行,起始列,结束列
        //标题行--数据质控表
        CellRangeAddress callRangeAddressTitle = new CellRangeAddress(0, 0, 0, 17);
        //质控对象名称
        CellRangeAddress callRangeAddressName = new CellRangeAddress(1, 1, 0, 3);
        //时间名称代号字段
        CellRangeAddress callRangeAddressOtherFiled = new CellRangeAddress(3, 4, 0, 0);
        CellRangeAddress callRangeAddressOtherTime = new CellRangeAddress(3, 4, 1, 1);
        CellRangeAddress callRangeAddressOtherName = new CellRangeAddress(3, 4, 2, 2);
        CellRangeAddress callRangeAddressOtherCode = new CellRangeAddress(3, 4, 3, 3);

        //质控要素
        //气温
        CellRangeAddress callRangeAddressTemp = new CellRangeAddress(3, 3, 4, 5);
        //气压
        CellRangeAddress callRangeAddressPressure = new CellRangeAddress(3, 3, 6, 7);
        //相对湿度
        CellRangeAddress callRangeAddressHumidity = new CellRangeAddress(3, 3, 8, 9);
        //降雨量
        CellRangeAddress callRangeAddressRain = new CellRangeAddress(3, 3, 10, 11);
        //能见度
        CellRangeAddress callRangeAddressVisual = new CellRangeAddress(3, 3, 12, 13);
        //风速
        CellRangeAddress callRangeAddressWindSpeed = new CellRangeAddress(3, 3, 14, 15);
        //风向
        CellRangeAddress callRangeAddressWindDir = new CellRangeAddress(3, 3, 16, 17);

        //填表人
        CellRangeAddress callRangeAddressRegister = new CellRangeAddress(dataList.size()+5, dataList.size()+5, 1, 2);
        //审核人
        CellRangeAddress callRangeAddressReviewer = new CellRangeAddress(dataList.size()+5, dataList.size()+5, 4, 7);
        //无需填充值的空白单元格
        CellRangeAddress callRangeAddressBlank = new CellRangeAddress(dataList.size()+5, dataList.size()+5, 8, 11);
        //打印日期
        CellRangeAddress callRangeAddressPrint = new CellRangeAddress(dataList.size()+5, dataList.size()+5, 12, 13);
        CellRangeAddress callRangeAddressTime = new CellRangeAddress(dataList.size()+5, dataList.size()+5, 14, 17);

        //创建工作表
        HSSFSheet sheet = workbook.createSheet("质控表");

        //加载上面创建的合并单元格对象
        sheet.addMergedRegion(callRangeAddressTitle);
        sheet.addMergedRegion(callRangeAddressName);
        sheet.addMergedRegion(callRangeAddressOtherFiled);
        sheet.addMergedRegion(callRangeAddressOtherTime);
        sheet.addMergedRegion(callRangeAddressOtherName);
        sheet.addMergedRegion(callRangeAddressOtherCode);
        sheet.addMergedRegion(callRangeAddressTemp);
        sheet.addMergedRegion(callRangeAddressPressure);
        sheet.addMergedRegion(callRangeAddressHumidity);
        sheet.addMergedRegion(callRangeAddressRain);
        sheet.addMergedRegion(callRangeAddressVisual);
        sheet.addMergedRegion(callRangeAddressWindSpeed);
        sheet.addMergedRegion(callRangeAddressWindDir);
        sheet.addMergedRegion(callRangeAddressRegister);
        sheet.addMergedRegion(callRangeAddressReviewer);
        sheet.addMergedRegion(callRangeAddressBlank);
        sheet.addMergedRegion(callRangeAddressPrint);
        sheet.addMergedRegion(callRangeAddressTime);

        //设置默认列宽
        sheet.setDefaultColumnWidth(12);
        sheet.setColumnWidth(1,20*256);
        //设置默认行高
        sheet.setDefaultRowHeight((short)350);

        //样式
        //水平居中，不加粗，加边框
        HSSFCellStyle cellStyle = createCellStyle(workbook, (short) 10, true,false,true);
        //水平居中，加粗，不加边框
        HSSFCellStyle cellStyleTitle = createCellStyle(workbook, (short) 13, true, true,false);
        //水平居中，不加粗，加边框
        HSSFCellStyle cellStyleHead = createCellStyle(workbook, (short) 10, true, true,false);
        //不水平居中，不加粗，不加边框
        HSSFCellStyle cellStyleBottom = createCellStyle(workbook, (short) 10, false, false,false);


        //接下来是为单元格填充值，分为两部分，一部分是固定值，单元格不会变的，另一部分是从数据库查出来的列表
        //如果是合并单元格，只需要填合并单元格的第一个单元格即可
        //标题行
        HSSFRow rowTitle = sheet.createRow(0);
        HSSFCell cellTitle = rowTitle.createCell(0);
        cellTitle.setCellStyle(cellStyleTitle);
        cellTitle.setCellValue("海洋站数据质控表");

        HSSFRow roweName = sheet.createRow(1);
        HSSFCell cellName = roweName.createCell(0);
        cellName.setCellValue("观测站点名称：0546汕尾海洋站");

        HSSFRow roweOtherFiled = sheet.createRow(3);
        HSSFCell cellObserveTime = roweOtherFiled.createCell(1);
        cellObserveTime.setCellStyle(cellStyleHead);
        cellObserveTime.setCellValue("观测时间");
        HSSFCell cellStationName = roweOtherFiled.createCell(2);
        cellStationName.setCellStyle(cellStyleHead);
        cellStationName.setCellValue("站点名称");
        HSSFCell cellCode = roweOtherFiled.createCell(3);
        cellCode.setCellStyle(cellStyleHead);
        cellCode.setCellValue("站代码");
        HSSFCell cellTemp = roweOtherFiled.createCell(4);
        cellTemp.setCellStyle(cellStyleHead);
        cellTemp.setCellValue("气温(0.1℃)");
        HSSFCell cellPre = roweOtherFiled.createCell(6);
        cellPre.setCellStyle(cellStyleHead);
        cellPre.setCellValue("本站气压(0.1hPa)");
        HSSFCell cellHumidity = roweOtherFiled.createCell(8);
        cellHumidity.setCellStyle(cellStyleHead);
        cellHumidity.setCellValue("相对湿度(%)");
        HSSFCell cellRain = roweOtherFiled.createCell(10);
        cellRain.setCellStyle(cellStyleHead);
        cellRain.setCellValue("降水量(0.1mm)");
        HSSFCell cellVisual = roweOtherFiled.createCell(12);
        cellVisual.setCellStyle(cellStyleHead);
        cellVisual.setCellValue("能见度(0.1℃)");
        HSSFCell cellWindSpeed = roweOtherFiled.createCell(14);
        cellWindSpeed.setCellStyle(cellStyleHead);
        cellWindSpeed.setCellValue("风速(m/s)");
        HSSFCell cellWindDir = roweOtherFiled.createCell(16);
        cellWindDir.setCellStyle(cellStyleHead);
        cellWindDir.setCellValue("风向(°)");


        HSSFRow rowValue = sheet.createRow(4);
        for (int i = 4; i <= 17; i++) {
            HSSFCell cell = rowValue.createCell(i);
            cell.setCellStyle(cellStyleHead);
            if (i % 2 == 0) {
                cell.setCellValue("修订值");
            } else {
                cell.setCellValue("原始值");
            }
        }

        //在数据表格之后
        HSSFRow rowRegister = sheet.createRow(dataList.size()+5);
        HSSFCell cellRegister = rowRegister.createCell(0);
        cellRegister.setCellStyle(cellStyleBottom);
        cellRegister.setCellValue("填表人：");
        HSSFCell cellRegisterValue = rowRegister.createCell(1);
        cellRegisterValue.setCellStyle(cellStyleBottom);
        cellRegisterValue.setCellValue("xxx");
        HSSFCell cellReviewer = rowRegister.createCell(3);
        cellReviewer.setCellStyle(cellStyleBottom);
        cellReviewer.setCellValue("审核人：");
        HSSFCell cellReviewerValue = rowRegister.createCell(4);
        cellReviewerValue.setCellStyle(cellStyleBottom);
        cellReviewerValue.setCellValue("***");
        HSSFCell cellPrint = rowRegister.createCell(12);
        cellPrint.setCellStyle(cellStyleBottom);
        cellPrint.setCellValue("打印日期：");
        HSSFCell cellDate = rowRegister.createCell(14);
        cellDate.setCellStyle(cellStyleBottom);
        cellDate.setCellValue(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(new Date()));


        //创建数据行
        if (dataList != null) {
            int a = 1;
            for (int i = 0; i < dataList.size(); i++) {
                Data data = dataList.get(i);
                //从第五行开始
                HSSFRow rowData = sheet.createRow(i + 5);
                HSSFCell cellNumValue = rowData.createCell(0);
                cellNumValue.setCellStyle(cellStyle);
                cellNumValue.setCellValue(a++);

                HSSFCell cellObserveTimeValue = rowData.createCell(1);
                cellObserveTimeValue.setCellStyle(cellStyle);
                cellObserveTimeValue.setCellValue(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(data.getObserveTime()));

                HSSFCell cellStationNameValue = rowData.createCell(2);
                cellStationNameValue.setCellStyle(cellStyle);
                cellStationNameValue.setCellValue(data.getStationName());

                HSSFCell cellStationCodeValue = rowData.createCell(3);
                cellStationCodeValue.setCellStyle(cellStyle);
                cellStationCodeValue.setCellValue(data.getStationCode());

                HSSFCell cellTempChangeValue = rowData.createCell(4);
                cellTempChangeValue.setCellStyle(cellStyle);
                cellTempChangeValue.setCellValue(data.getTempChangeValue());

                HSSFCell cellRawChangeValue = rowData.createCell(5);
                cellRawChangeValue.setCellStyle(cellStyle);
                cellRawChangeValue.setCellValue(data.getTempRawValue());

                HSSFCell cellPressChangeValue = rowData.createCell(6);
                cellPressChangeValue.setCellStyle(cellStyle);
                cellPressChangeValue.setCellValue(data.getPressChangeValue());

                HSSFCell cellPressRowValue = rowData.createCell(7);
                cellPressRowValue.setCellStyle(cellStyle);
                cellPressRowValue.setCellValue(data.getPressRawValue());

                HSSFCell cellHumidityChangeValue = rowData.createCell(8);
                cellHumidityChangeValue.setCellStyle(cellStyle);
                cellHumidityChangeValue.setCellValue(data.getHumidityChangeValue());

                HSSFCell cellHumidityRawValue = rowData.createCell(9);
                cellHumidityRawValue.setCellStyle(cellStyle);
                cellHumidityRawValue.setCellValue(data.getHumidityRawValue());

                HSSFCell cellRainChangeValue = rowData.createCell(10);
                cellRainChangeValue.setCellStyle(cellStyle);
                cellRainChangeValue.setCellValue(data.getRainChangeValue());

                HSSFCell cellRainRawValue = rowData.createCell(11);
                cellRainRawValue.setCellStyle(cellStyle);
                cellRainRawValue.setCellValue(data.getRainRawValue());

                HSSFCell cellVisualChangeValue = rowData.createCell(12);
                cellVisualChangeValue.setCellStyle(cellStyle);
                cellVisualChangeValue.setCellValue(data.getVisualChangeValue());

                HSSFCell cellVisualRawValue = rowData.createCell(13);
                cellVisualRawValue.setCellStyle(cellStyle);
                cellVisualRawValue.setCellValue(data.getVisualRawValue());

                HSSFCell cellWindSpeedChangeValue = rowData.createCell(14);
                cellWindSpeedChangeValue.setCellStyle(cellStyle);
                cellWindSpeedChangeValue.setCellValue(data.getWindSpeedChangeValue());

                HSSFCell cellWindSpeedRawValue = rowData.createCell(15);
                cellWindSpeedRawValue.setCellStyle(cellStyle);
                cellWindSpeedRawValue.setCellValue(data.getWindSpeedRawValue());

                HSSFCell cellWindDirChangeValue = rowData.createCell(16);
                cellWindDirChangeValue.setCellStyle(cellStyle);
                cellWindDirChangeValue.setCellValue(data.getWindDirChangeValue());

                HSSFCell cellWindDirRawValue = rowData.createCell(17);
                cellWindDirRawValue.setCellStyle(cellStyle);
                cellWindDirRawValue.setCellValue(data.getWindDirRawValue());
            }
        }
        return workbook;
    }

    /**
     * 单元格样式
     * @param workbook
     * @param fontSize
     * @param flag
     * @return
     */
    private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, short fontSize,boolean flag,boolean flag1,boolean flag2 ) {

        HSSFCellStyle style = workbook.createCellStyle();
        //是否水平居中
        if (flag) {
            style.setAlignment(HorizontalAlignment.CENTER);
        }

        //垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        //创建字体
        HSSFFont font = workbook.createFont();
        //是否加粗字体
        font.setBold(flag1);
        font.setFontHeightInPoints(fontSize);
        //加载字体
        style.setFont(font);
        //是否加上边框
        if (flag2) {
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
        }
        return style;
    }
}

