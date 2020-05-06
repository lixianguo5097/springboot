package com.lxg.model.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.util.Date;

/**
 * @author LXG
 * @date 2020-4-30
 */
@Data
public class Fruit {
    @ExcelProperty(value = "物品种类", index = 0)
    private String category;
    @ExcelProperty(value = "水果名称", index = 1)
    private String fruit;
    @ExcelProperty(value = "水果颜色", index = 2)
    private String color;
    @ExcelProperty(value = "水果产期", index = 3)
    @ColumnWidth(20)
    private Date produceDate;

    public Fruit(String category, String fruit, String color, Date produceDate) {
        this.category = category;
        this.fruit = fruit;
        this.color = color;
        this.produceDate = produceDate;
    }

    public Fruit() {
    }
}
