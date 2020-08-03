package com.xiayuan.sparkProject.util;

import java.math.BigDecimal;

/**
 * 数字工具类
 * 数字工具类可以根据传入的参数得到保留相应位数的小数。
 *
 * @author yeunsher
 * @date 2020-03-25 - 16:09
 */
public class NumberUtils {

    /**
     * 格式化小数
     * @param num 字符串
     * @param scale 四舍五入的位数
     * @return 格式化小数
     */
    public static double formatDouble(double num, int scale){
        BigDecimal bigDecimal = new BigDecimal(num);
        return bigDecimal.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
