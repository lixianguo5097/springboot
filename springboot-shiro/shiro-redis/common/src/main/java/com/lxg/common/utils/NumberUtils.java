package com.lxg.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import static java.math.BigDecimal.ROUND_FLOOR;

/**
 * @author Czh
 * Date: 2018/8/17 上午9:50
 */
public class NumberUtils {

    private NumberUtils() {
    }

    /**
     * 格式化
     *
     * @param value
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return
     */
    public static double keepScale(double value, int scale) {
        return  keepScale(BigDecimal.valueOf(value), scale).doubleValue();
    }

    /**
     * 格式化
     *
     * @param value
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return
     */
    public static BigDecimal keepScale(BigDecimal value, int scale) {
        return  value.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }


    /**
     * 除法
     *
     * @param value1 被除数
     * @param value2 除数
     * @param scale  表示表示需要精确到小数点以后几位。
     * @return
     */
    public static double divide(double value1, double value2, int scale) {
        return NumberUtils.divide(BigDecimal.valueOf(value1), BigDecimal.valueOf(value2), scale).doubleValue();
    }

    /**
     * 除法
     *
     * @param value1 被除数
     * @param value2 除数
     * @param scale  表示表示需要精确到小数点以后几位。
     * @return
     */
    public static BigDecimal divide(BigDecimal value1, BigDecimal value2, int scale) {
        return value1.divide(value2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 除法
     *
     * @param value1 被除数
     * @param value2 除数
     * @param scale  表示表示需要精确到小数点以后几位。
     * @return
     */
    public static double divide4RoundDown(double value1, double value2, int scale) {
        return divide4RoundDown(BigDecimal.valueOf(value1), BigDecimal.valueOf(value2), scale).doubleValue();
    }
    public static BigDecimal divide4RoundDown(BigDecimal value1, BigDecimal value2, int scale) {
        return value1.divide(value2, scale, BigDecimal.ROUND_DOWN);
    }

    /**
     * 提供精确的加法运算。
     *
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static double add(double value1, double value2) {
        return NumberUtils.add(BigDecimal.valueOf(value1), BigDecimal.valueOf(value2)).doubleValue();
    }

    /**
     * 提供精确的加法运算。
     *
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和,BigDecimal型
     */
    public static BigDecimal add(BigDecimal value1, BigDecimal value2) {
        if (value1 == null) {
            value1 = new BigDecimal(0);
        }
        if (value2 == null) {
            value2 = new BigDecimal(0);
        }
        return value1.add(value2);
    }

    /**
     * 提供精确的减法运算。
     *
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差
     */
    public static double subtract(double value1, double value2) {
        return NumberUtils.subtract(BigDecimal.valueOf(value1), BigDecimal.valueOf(value2)).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差,返回BigDecimal型
     */
    public static BigDecimal subtract(BigDecimal value1, BigDecimal value2) {
        return value1.subtract(value2);
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static double multiply(double value1, double value2) {
        return NumberUtils.multiply(BigDecimal.valueOf(value1), BigDecimal.valueOf(value2)).doubleValue();
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static BigDecimal multiply(BigDecimal value1, BigDecimal value2) {
        return value1.multiply(value2);
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param value1 被乘数
     * @param value2 乘数
     * @param scale 保留几位小数
     * @return 两个参数的积
     */
    public static BigDecimal multiply(BigDecimal value1, BigDecimal value2, int scale) {
        BigDecimal value = value1.multiply(value2);
        value.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return value;
    }

    /**
     * 精确对比两个数字
     *
     * @param value1 需要被对比的第一个数
     * @param value2 需要被对比的第二个数
     * @return 如果两个数一样则返回0，如果第一个数比第二个数大则返回1，反之返回-1
     */
    public static int compareTo(double value1, double value2) {
        return NumberUtils.compareTo(BigDecimal.valueOf(value1), BigDecimal.valueOf(value2));
    }

    /**
     * 精确对比两个数字
     *
     * @param value1 需要被对比的第一个数
     * @param value2 需要被对比的第二个数
     * @return 如果两个数一样则返回0，如果第一个数比第二个数大则返回1，反之返回-1
     */
    public static int compareTo(BigDecimal value1, BigDecimal value2) {
        return value1.compareTo(value2);
    }

    /**
     * 把字符串转成BigDecimal
     * @param str
     * @return
     */
    public static BigDecimal convertStringToBigDecimal(String str) {
        return  new BigDecimal(str);
    }

    /**
     *  去除金额多余的零
     * @return
     */
    public static String formatBigDecimalString(BigDecimal bigDecimal){
        if (bigDecimal != null) {
            String bigDecimalStr = bigDecimal.toPlainString();
            if(bigDecimalStr.indexOf(".") > 0){
                //正则表达
                //去掉后面无用的零
                bigDecimalStr = bigDecimalStr.replaceAll("0+?$", "");
                //如小数点后面全是零则去掉小数点
                bigDecimalStr = bigDecimalStr.replaceAll("[.]$", "");
            }

            return bigDecimalStr;
        }
        return null;
    }

    /**
     * 向下取两位小数
     *
     * @return
     */
    public static BigDecimal formatRoundDown(BigDecimal decimal) {
        return decimal.setScale(2, BigDecimal.ROUND_DOWN);
    }

    /**
     * 正数转为负数
     *
     * @param decimal
     * @return
     */
    public static BigDecimal negative(BigDecimal decimal) {
        return BigDecimal.ZERO.subtract(decimal);
    }

    /**
     * 空对象转为Zero
     *
     * @param value
     * @return
     */
    public static BigDecimal nullToZero(BigDecimal value) {
        if (value == null) {
            return BigDecimal.ZERO;
        }

        return value;
    }

    /**
     * 格式化成价格金额显示，千分位逗号，两位小数，向下取精度
     *
     * @param value
     * @return
     */
    public static String formatPriceMoney(BigDecimal value) {
        DecimalFormat df = new DecimalFormat();
        // 将格式应用于格式化器
        df.applyPattern(",##0.00");
        return df.format(value.setScale(2, ROUND_FLOOR));
    }

    /**
     * value1 > value2
     * @param value1
     * @param value2
     * @return
     */
    public static boolean gt(BigDecimal value1, BigDecimal value2) {
        if (value1 == null) {
            return false;
        }
        if (value2 == null) {
            return true;
        }

        if(value1.compareTo(value2) == 1) {
            return true;
        }

        return false;
    }

    /**
     * 除法
     *
     * @param value 被除数
     * @param scale  表示表示需要精确到小数点以后几位。
     * @return
     */
    public static BigDecimal divide(BigDecimal value, int scale) {
        return NumberUtils.divide(value, new BigDecimal(100), scale);
    }

    /**
     * 字符串转BigDecimal 并除以100
     *
     * @param strNumber
     * @return
     */
    public static BigDecimal strToDecimalYuan(String strNumber) {
        return  divide(new BigDecimal(strNumber), 2);
    }

}