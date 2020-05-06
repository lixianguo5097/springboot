package com.lxg.model.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

/**
 *
 * EasyExcel的样式是在对应实体类中加注解实现的
 *
 * @ContentRowHeight(10) 内容行高
 * @HeadRowHeight(20) 标题行行高
 * @ColumnWidth(25) 列换
 *
 * @author LXG
 * @date 2020-4-30
 */
@Data
@ContentRowHeight(20)
@HeadRowHeight(20)
@ColumnWidth(25)
public class EasyUser {

    /**
     * @ExcelProperty(value ="用户ID",index = 0)
     * @ExcelProperty  value用于显示标题栏，如果不加，默认为字段名;index：输出到指定的列索引从0开始
     * 第二四六列为空
     */

    /**
     * 主标题 将整合为一个单元格效果如下：
     * @ExcelProperty(value = {"主标题","用户ID"})
     * —————————————————————————
     * |          主标题        |
     * —————————————————————————
     * |字符串标题|日期标题|数字标题|
     * —————————————————————————
     */
    /**
     * @ColumnWidth(50) 这一列宽度为50,覆盖上面的宽度25
     */
    @ExcelProperty(value = {"主标题","用户ID"})
    @ColumnWidth(50)
    private String userId;
    @ExcelProperty(value = {"主标题","姓名"})
    private String name;
    @ExcelProperty(value ={"主标题","年龄"})
    private Integer age;
    @ExcelProperty(value = {"主标题","性别"})
    private String sex;

    public EasyUser(String userId, String name, Integer age, String sex) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public EasyUser() {
    }
}
